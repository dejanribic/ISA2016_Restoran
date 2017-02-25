package com.desha.Repositories;

import com.desha.Beans.Friend;
import com.desha.Beans.FriendKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface FriendRepository extends JpaRepository<Friend, FriendKey> {

    ArrayList<Friend> findByGuestEmailAndAccepted(String email, int accepted);

    ArrayList<Friend> findByFriendEmailAndAccepted(String email, int accepted);

    Friend findByGuestEmailAndFriendEmail(String email1, String email2);
}
