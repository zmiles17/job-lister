package com.talentpath.JobLister.services;

import com.talentpath.JobLister.models.Listing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles({"serviceTest"})
public class JobListingServiceInMemDaoTests {

    @Autowired
    JobListingService service;

    @Test
    void gettingAllListingsShouldReturnAnEmptyListWhenNoListingsAreAvailable() {
        List<Listing> listings = service.getAllListings();
        assertEquals(0, listings.size());
    }
    
}
