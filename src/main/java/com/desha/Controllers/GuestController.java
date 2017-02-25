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
        System.out.println(email);
        System.out.println(friendSearch);

        Guest guest = guests.findByEmail(email);
        ArrayList<Guest> possibleFriends;

        // Ako sadrzi razmak (ime i prezime)
        if (friendSearch.contains(" ")) {
            String name = friendSearch.split(" ")[0];
            String surname = friendSearch.split(" ")[1];
            System.out.println("name = " + name);
            System.out.println("surname = " + surname);

            possibleFriends = guests.findByNameAndSurname(name, surname);
        }

        // Ako ne sadrzi razmak (jedna rec - ime)
        else {
            possibleFriends = guests.findByName(friendSearch);
        }

        for (Guest g : possibleFriends) {
            System.out.println(g.getName());
            System.out.println(g.getSurname());
            System.out.println(g.getEmail());
        }

        List<Guest> allGuests = guests.findAll();

        ArrayList<Guest> addableGuests = new ArrayList<>();
        return null;
        /*
        for (Guest g : allGuests) {
            if (!guest.getFriends().contains(g))
                if (!guest.getRequests().contains(g))
                    if (!g.getRequests().contains(guest))
                        addableGuests.add(g);
        }
        return addableGuests;
        */

    }

    /*

    @RequestMapping(value = "/requests/{id}")
    public List<Guest> getAllRequests(@PathVariable long id) {
        Guest guest = repository.findOne(id);
        if (guest != null)
            return guest.getRequests();
        else
            return null;
    }





    @RequestMapping(value = "addFriend/{id}/{friend}")
    public void addFriend(@PathVariable long id, @PathVariable long friend) {
        Guest guest = repository.findOne(id);
        Guest newFriend = repository.findOne(friend);
        if (guest != null & newFriend != null) {
            List<Guest> currentFriends = guest.getFriends();
            currentFriends.add(newFriend);
            guest.setFriends(currentFriends);
            repository.save(guest);

            List<Guest> currentFriends2 = newFriend.getFriends();
            currentFriends2.add(guest);
            newFriend.setFriends(currentFriends2);
            repository.save(newFriend);
        }
    }

    @RequestMapping(value = "sendRequest/{id}/{friend}")
    public void sendRequest(@PathVariable long id, @PathVariable long friend) {
        Guest guest = repository.findOne(id);
        Guest requested = repository.findOne(friend);

        if (guest != null & requested != null) {
            if (!guest.getFriends().contains(requested) & !requested.getFriends().contains(guest) & !guest.getRequests().contains(requested) & !requested.getRequests().contains(guest)) {
                requested.getRequests().add(guest);
                repository.save(requested);
            }
        }

    }

    @RequestMapping(value = "acceptRequest/{id}/{friend}")
    public void acceptRequest(@PathVariable long id, @PathVariable long friend) {
        Guest guest = repository.findOne(id);
        Guest requested = repository.findOne(friend);
        if (guest != null & requested != null) {
            if (guest.getRequests().contains(requested)) {
                guest.getRequests().remove(requested);
                if (!guest.getFriends().contains(requested)) {
                    guest.getFriends().add(requested);
                    requested.getFriends().add(guest);
                    System.out.println("Dodao sam!");
                    repository.save(guest);
                    repository.save(requested);
                }
            }
        }
    }

    @RequestMapping(value = "/change", method = RequestMethod.PUT)
    public void change(@RequestBody Guest guest) {
        repository.save(guest);
    }

    @RequestMapping(value = "/changeImage/{id}", method = RequestMethod.PUT)
    public void changeImage(@PathVariable long id, @RequestBody Blob image) {
        //Guest guest = repository.findOne(id);
        //guest.setImage(image);
        //repository.save(guest);
    }

    @RequestMapping(value = "declineRequest/{id}/{friend}")
    public void declineRequest(@PathVariable long id, @PathVariable long friend) {
        Guest guest = repository.findOne(id);
        Guest requested = repository.findOne(friend);
        if (guest != null & requested != null) {
            if (guest.getRequests().contains(requested) & !guest.getFriends().contains(requested)) {
                guest.getRequests().remove(requested);
                repository.save(guest);
            }
        }
    }

    @RequestMapping(value = "deleteFriend/{id}/{friend}")
    public void deleteFriend(@PathVariable long id, @PathVariable long friend) {
        Guest guest = repository.findOne(id);
        Guest requested = repository.findOne(friend);
        if (guest != null & requested != null) {
            if (guest.getFriends().contains(requested) & requested.getFriends().contains(guest)) {
                guest.getFriends().remove(requested);
                requested.getFriends().remove(guest);
                repository.save(guest);
                repository.save(requested);
            }
        }

    }
    */
}
