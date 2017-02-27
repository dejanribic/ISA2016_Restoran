package com.desha.Controllers;

import com.desha.Beans.Guest;
import com.desha.Beans.Invite;
import com.desha.Beans.Reservation;
import com.desha.Repositories.FriendRepository;
import com.desha.Repositories.GuestRepository;
import com.desha.Repositories.InviteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/invitations")
public class InviteController {

    private InviteRepository repository;
    private GuestRepository guestRepository;
    private FriendRepository friendRepository;


    @Autowired
    public InviteController(GuestRepository guestRepository, InviteRepository repository) {
        this.guestRepository = guestRepository;
        this.repository = repository;
    }

    @RequestMapping(value = "/all")
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
        invite.setAccepted(false);
        invite.setGuestEmail(host.getEmail());
        invite.setFriendEmail(invitedGuest.getEmail());
        invite.setRestaurantName(reservation.getRestaurantName());
        invite.setReservationId(reservation.getId());

        repository.save(invite);
    }

    @RequestMapping(value = "/getInvited", method = RequestMethod.PUT)
    public List<Invite> getInvited(@RequestBody Reservation reservation) {
        System.out.println("-----");
        System.out.println("reservation: " + reservation.toString());
        List<Invite> spisakBrt = repository.findByReservationId(reservation.getId());
        for (Invite i : spisakBrt) {
            System.out.println("invite: " + i.toString());
        }
        return spisakBrt;
    }

    /*

    @RequestMapping(value = "/activeConfirmed/{id}")
    public List<Invite> getActiveConfimedReservations(@PathVariable long id) {
        Guest guest = guestRepository.findOne(id);
        List<Invite> Invites = repository.findByInvited(guest);
        ArrayList<Invite> activeConfirmed = new ArrayList<>();
        for (Invite inv : Invites)
            if (inv.getReservation().getDateTime().after(new Date()))
                if (inv.isConfirmed())
                    activeConfirmed.add(inv);
        return activeConfirmed;
    }

    @RequestMapping(value = "/activeNotConfirmed/{id}")
    public List<Invite> getActiveNotConfimedReservations(@PathVariable long id) {
        Guest guest = guestRepository.findOne(id);
        List<Invite> Invites = repository.findByInvited(guest);
        ArrayList<Invite> activeConfirmed = new ArrayList<>();
        for (Invite inv : Invites)
            if (inv.getReservation().getDateTime().after(new Date()))
                if (!inv.isConfirmed())
                    activeConfirmed.add(inv);
        return activeConfirmed;
    }

    @RequestMapping(value = "/confirmInvite", method = RequestMethod.PUT)
    public void confirmReservation(@RequestBody Invite Invite) {
        Invite.setConfirmed(true);
        repository.save(Invite);
    }


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

    @RequestMapping(value = "/invitableFriends", method = RequestMethod.PUT)
    public List<Guest> getInvitableFriends(@RequestBody Reservation reservation) {
        ArrayList<Guest> invitable = new ArrayList<>();
        Guest host = guestRepository.findOne(reservation.getHost().getUser_id());

        for (Guest friend : host.getFriends()) {
            if (repository.findByInvitedAndReservation(friend, reservation) == null)
                invitable.add(friend);
        }
        return invitable;
    }







    */
}
