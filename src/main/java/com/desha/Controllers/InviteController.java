package com.desha.Controllers;

import com.desha.Beans.Friend;
import com.desha.Beans.Guest;
import com.desha.Beans.Invite;
import com.desha.Beans.Reservation;
import com.desha.Repositories.FriendRepository;
import com.desha.Repositories.GuestRepository;
import com.desha.Repositories.InviteRepository;
import com.desha.Repositories.ReservationRepository;
import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.web.bind.annotation.*;

import javax.persistence.LockModeType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/invitations")
public class InviteController {

    private ReservationRepository reservationRepository;
    private InviteRepository repository;
    private GuestRepository guestRepository;
    private FriendRepository friendRepository;

    @Autowired
    public InviteController(GuestRepository guestRepository, InviteRepository repository, FriendRepository friendRepository, ReservationRepository reservationRepository) {
        this.guestRepository = guestRepository;
        this.repository = repository;
        this.friendRepository = friendRepository;
        this.reservationRepository = reservationRepository;
    }

    @Value("${sendGridAPIKey}")
    private String sendGridAPIKey;

    @RequestMapping(value = "/all")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    public List<Invite> getAll() {
        return repository.findAll();
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT)
    public void addReservation(@RequestBody Invite Invite) {
        repository.save(Invite);
    }

    @RequestMapping(value = "/add/{email:.+}", method = RequestMethod.PUT)
    public void addById(@RequestBody Reservation reservation, @PathVariable String email) {
        Guest host = guestRepository.findByEmail(reservation.getGuestEmail());
        Guest invitedGuest = guestRepository.findByEmail(email);

        Invite invite = new Invite();
        invite.setGuestEmail(host.getEmail());
        invite.setFriendEmail(invitedGuest.getEmail());
        invite.setRestaurantName(reservation.getRestaurantName());
        invite.setReservationId(reservation.getId());

        if (reservation.getStart().before(new Date())) {
            invite.setAccepted(true);
            repository.save(invite);
        } else {

            invite.setAccepted(false);

            repository.save(invite);

            // Slanje mail-a:

            // Regular EMAIL
            String contentString = "Hello, welcome to the \"ISA 2016\" Restaurant app! \n \n";
            contentString += host.getName() + " " + host.getSurname() + " has invited you to a restaurant with them! \n \n";
            contentString += "After logging in, please click the following link to confirm or cancel the inviration:\n\n";
            contentString += "http://localhost:3000/#/pozivnice" + "\n\n Thanks!";

            // HTML EMAIL
            // String contentString = "<h2>Hello, welcome to the \"ISA 2016\" Restaurant app! \n \n Please confirm your  = = = email address by clicking on the following link:</h2>\n\n<a href=\"localhost:3000/users/confirmUser/" + newGuest.getEmail() + "\">Click me!</a>\n\n";

            Content content = new Content("text/html", contentString);
            //Content content = new Content("text/plain", contentString);

            Email from = new Email("ISA.DAEMON@ISA2016.BRT");
            Email to = new Email(invitedGuest.getEmail());

            String subject = "ISA 2016 - User confirmation";

            Mail mail = new Mail(from, subject, to, content);

            SendGrid sg = new SendGrid(sendGridAPIKey);
            Request request = new Request();

            if (invitedGuest.getEmail().equals("dejanribic021@gmail.com")) {
                try {
                    request.method = Method.POST;
                    request.endpoint = "mail/send";
                    request.body = mail.build();
                    Response response = sg.api(request);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    @RequestMapping(value = "/getInvited", method = RequestMethod.PUT)
    public List<Invite> getInvited(@RequestBody Reservation reservation) {
        return repository.findByReservationId(reservation.getId());
    }

    @RequestMapping(value = "/invitableFriends", method = RequestMethod.PUT)
    public List<Guest> getInvitableFriends(@RequestBody Reservation reservation) {
        // Host rezervacije
        Guest host = guestRepository.findByEmail(reservation.getGuestEmail());

        // Sva prijateljstva
        ArrayList<Friend> allFriendshipsOfHost = friendRepository.findByGuestEmailAndAccepted(host.getEmail(), 1);


        // Svi prijatelji
        ArrayList<Guest> allFriendsOfHost = new ArrayList<>();
        for (Friend friendship : allFriendshipsOfHost) {
            allFriendsOfHost.add(guestRepository.findByEmail(friendship.getFriendMail()));
        }

        //Svi pozivi
        ArrayList<Invite> currentInvites = repository.findByReservationId(reservation.getId());

        //Svi vec pozvani prijatelji
        ArrayList<Guest> invitedFriendsOfHost = new ArrayList<>();
        for (Invite invite : currentInvites) {
            invitedFriendsOfHost.add(guestRepository.findByEmail(invite.getFriendEmail()));
        }

        //Razlika svih i svih pozvanih
        allFriendsOfHost.removeAll(invitedFriendsOfHost);

        // Novo "ime" za spisak
        ArrayList<Guest> inviteable = allFriendsOfHost;

        return inviteable;
    }

    @RequestMapping(value = "/activeNotConfirmed/{email:.+}")
    public List<Invite> getUnconfirmedInvites(@PathVariable String email) {
        ArrayList<Invite> invites = repository.findByFriendEmailAndAccepted(guestRepository.findByEmail(email).getEmail(), false);
        ArrayList<Invite> finalInvites = new ArrayList<>();
        Date now = new Date();
        for (Invite i : invites) {
            if (now.before(reservationRepository.findById(i.getReservationId()).getStart()))
                finalInvites.add(i);
        }
        return finalInvites;
    }

    @RequestMapping(value = "/activeConfirmed/{email:.+}")
    public List<Invite> getConfimedInvites(@PathVariable String email) {
        Guest me = guestRepository.findByEmail(email);
        return repository.findByFriendEmailAndAccepted(me.getEmail(), true);
    }

    @RequestMapping(value = "/confirmInvite", method = RequestMethod.PUT)
    public void confirmInvite(@RequestBody Invite invite) {
        Invite confirmedInvite = repository.findByReservationIdAndGuestEmailAndFriendEmailAndRestaurantNameAndAccepted(invite.getReservationId(), invite.getGuestEmail(), invite.getFriendEmail(), invite.getRestaurantName(), invite.isAccepted());
        confirmedInvite.setAccepted(true);
        repository.save(confirmedInvite);
    }

    @RequestMapping(value = "/cancelInvite", method = RequestMethod.PUT)
    public void cancelInvite(@RequestBody Invite invite) {
        Invite confirmedInvite = repository.findByReservationIdAndGuestEmailAndFriendEmailAndRestaurantNameAndAccepted(invite.getReservationId(), invite.getGuestEmail(), invite.getFriendEmail(), invite.getRestaurantName(), invite.isAccepted());
        repository.delete(confirmedInvite);
    }

    /*
    @RequestMapping(value = "/pastVisits/{id}")
    public List<Reservation> getInactiveReservations(@PathVariable long id) {
        Guest guest = guestRepository.findOne(id);
        List<Invite> Invites = repository.findByInvited(guest);
        ArrayList<Reservation> reservations = new ArrayList<>();
        for (Invite inv : Invites)
            if (inv.getReservation().getDateTime().before(new Date()))
                if (inv.isConfirmed())
                    reservations.add(inv.getReservation());
        return reservations;
    }









    */
}
