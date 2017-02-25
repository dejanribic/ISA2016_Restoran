package com.desha.Repositories;

import com.desha.Beans.Friend;
import com.desha.Beans.FriendKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, FriendKey> {

    List<Friend> findByGuestEmail(String email);

    List<Friend> findByGuestEmailAndAccepted(String email, int accepted);

    Friend findByGuestEmailAndFriendEmail(String email1, String email2);
}
