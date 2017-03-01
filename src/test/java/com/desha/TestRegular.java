package com.desha;

import com.desha.Beans.Guest;
import com.desha.Repositories.GuestRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

/**
 * Created by DEJAN on 01/03/17 at 20:24.
 */

@Transactional
public class TestRegular {

    @Autowired
    private GuestRepository guestRepository;

    @Before
    public void setUp() {
        // before start
    }

    @After
    public void tearDown() {
        // clean up
    }

    @Test
    public void testFindByEmail() {
        Guest desha = guestRepository.findByEmail("dejanribic021@gmail.com");
        Assert.assertNotNull("Fail - expected not null", desha);
        Assert.assertEquals("Fail - expected exact string", desha, "dejanribic021@gmail.com");
    }
}
