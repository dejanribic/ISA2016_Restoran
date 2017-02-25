package com.desha.Controllers;


import com.desha.Beans.Friend;
import com.desha.Beans.Guest;
import com.desha.Repositories.FriendRepository;
import com.desha.Repositories.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping(value = "/guests")
public class GuestController {

    private GuestRepository guests;
    private FriendRepository friends;

    @Autowired
    public GuestController(GuestRepository guests, FriendRepository friends) {
        this.guests = guests;
        this.friends = friends;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<Guest> getAll() {
        return guests.findAll();
    }

    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public void create(@RequestBody Guest newGuest) {
        guests.save(newGuest);
    }

    @RequestMapping(value = "/friends/{email:.+}")
    public List<Guest> getAllFriends(@PathVariable String email) {

        //List<Friend> friendsOfGuest = friends.findByGuestEmail(email);
        List<Friend> friendsOfGuest = friends.findByGuestEmailAndAccepted(email, 1);

        ArrayList<Guest> guestFriends = new ArrayList<>();

        for (Friend f : friendsOfGuest) {
            guestFriends.add(guests.findByEmail(f.getFriendMail()));
        }
        return guestFriends;
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public void change(@RequestBody Guest guest) {
        guests.save(guest);
    }

    @RequestMapping(value = "deleteFriend/{myEmail:.+}/{friendEmail:.+}")
    public void deleteFriend(@PathVariable String myEmail, @PathVariable String friendEmail) {
        Guest guest = guests.findByEmail(myEmail);
        Guest friend = guests.findByEmail(friendEmail);

        if (guest != null & friend != null) {
            friends.delete(friends.findByGuestEmailAndFriendEmail(guest.getEmail(), friend.getEmail()));
            friends.delete(friends.findByGuestEmailAndFriendEmail(friend.getEmail(), guest.getEmail()));
        }
    }

    @RequestMapping(value = "/addable/{email:.+}/{friendSearch}")
    public List<Guest> getAddableFriends(@PathVariable String email, @PathVariable String friendSearch) {

        ArrayList<Guest> possibleFriends;
        ArrayList<Guest> addableFriends = new ArrayList<>();

        // Ako sadrzi razmak (ime i prezime)
        if (friendSearch.contains(" ")) {
            String name = friendSearch.split(" ")[0];
            String surname = friendSearch.split(" ")[1];
            possibleFriends = guests.findByNameAndSurname(name, surname);
        }

        // Ako ne sadrzi razmak (jedna rec - ime)
        else {
            possibleFriends = guests.findByName(friendSearch);
        }

        for (Guest possibleFriend : possibleFriends) {
            if (!email.equals(possibleFriend.getEmail())) {
                Friend f = friends.findByGuestEmailAndFriendEmail(email, possibleFriend.getEmail());
                if (f == null) {
                    addableFriends.add(possibleFriend);
                }
            }
        }

        return addableFriends;
    }

    @RequestMapping(value = "sendRequest/{email:.+}/{friendEmail:.+}")
    public void sendRequest(@PathVariable String email, @PathVariable String friendEmail) {
        Friend newFriendship = new Friend(email, friendEmail, 0);
        friends.save(newFriendship);
    }

    @RequestMapping(value = "/requests/{email:.+}")
    public List<Guest> getAllRequests(@PathVariable String email) {
        ArrayList<Guest> requestedFriends = new ArrayList<>();

        ArrayList<Friend> requests = friends.findByFriendEmailAndAccepted(email, 0);
        for (Friend f : requests) {
            requestedFriends.add(guests.findByEmail(f.getGuestMail()));
        }
        return requestedFriends;
    }

    @RequestMapping(value = "acceptRequest/{me:.+}/{friend:.+}")
    public void acceptRequest(@PathVariable String me, @PathVariable String friend) {
        Friend oldFriendship = friends.findByGuestEmailAndFriendEmail(friend, me);
        oldFriendship.setAccepted(1);

        Friend newFriendship = new Friend(me, friend, 1);

        friends.save(oldFriendship);
        friends.save(newFriendship);
    }

    @RequestMapping(value = "declineRequest/{me:.+}/{friend:.+}")
    public void declineRequest(@PathVariable String me, @PathVariable String friend) {
        Friend oldFriendship = friends.findByGuestEmailAndFriendEmail(friend, me);
        friends.delete(oldFriendship);
    }
}
