package com.talentpath.JobLister.dao;

import com.talentpath.JobLister.models.Listing;
import com.talentpath.JobLister.persistence.ListingDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("daoTesting")
public class ListingDaoTests {

    @Autowired
    ListingDao listingDao;

    @BeforeEach
    void setup() {
        listingDao.deleteAll();
    }

    @Test
    void createListing() {
        Listing listing = new Listing();
        listing.setListingName("Marine Biologist");
        listing.setIndustry("Environmental Science");
        listing.setCompany("Diver's Anonymous");
        listing.setCity("Ft. Myers");
        listing.setState("Florida");
        listing.setEmploymentType("Contract");
        listing.setSalary(40000);
        listing.setDatePosted(Instant.now());

        Listing savedListing = listingDao.save(listing);

        assertNotNull(savedListing.getListingId());
        assertEquals("Marine Biologist", savedListing.getListingName());
        assertEquals("Diver's Anonymous", savedListing.getCompany());
        assertEquals(40000, savedListing.getSalary());
        assertEquals("Environmental Science", savedListing.getIndustry());
        assertEquals("Contract", savedListing.getEmploymentType());
        assertEquals("Ft. Myers", savedListing.getCity());
        assertEquals("Florida", savedListing.getState());
        assertEquals(listing.getDatePosted(), savedListing.getDatePosted());
    }

    @Test
    void getListingById() {
        Listing listing = new Listing();
        listing.setListingName("Marine Biologist");
        listing.setIndustry("Environmental Science");
        listing.setCompany("Diver's Anonymous");
        listing.setCity("Ft. Myers");
        listing.setState("Florida");
        listing.setEmploymentType("Contract");
        listing.setSalary(40000);
        listing.setDatePosted(Instant.now());

        Listing savedListing = listingDao.save(listing);
        Listing fromDb = listingDao.findById(savedListing.getListingId()).get();

        assertEquals(savedListing, fromDb);
    }

    @Test
    void getListingsByCompany() {
        Listing listing = new Listing();
        listing.setListingName("Marine Biologist");
        listing.setIndustry("Environmental Science");
        listing.setCompany("Diver's Anonymous");
        listing.setCity("Ft. Myers");
        listing.setState("Florida");
        listing.setEmploymentType("Contract");
        listing.setSalary(40000);
        listing.setDatePosted(Instant.now());

        Listing savedListing = listingDao.save(listing);

        List<Listing> jobList = listingDao.findByCompanyContainingIgnoreCase("diver's anonymous").get();

        assertEquals(savedListing, jobList.get(0));
    }

    @Test
    void getListingsBySalaryRange() {
        Listing listing = new Listing();
        listing.setListingName("Marine Biologist");
        listing.setIndustry("Environmental Science");
        listing.setCompany("Diver's Anonymous");
        listing.setCity("Ft. Myers");
        listing.setState("Florida");
        listing.setEmploymentType("Contract");
        listing.setSalary(40000);
        listing.setDatePosted(Instant.now());

        Listing savedListing = listingDao.save(listing);

        List<Listing> jobList = listingDao.findBySalaryBetween(30000, 50000).get();

        assertEquals(savedListing, jobList.get(0));
    }

    @Test
    void getListingsByCityAndState() {
        Listing listing = new Listing();
        listing.setListingName("Marine Biologist");
        listing.setIndustry("Environmental Science");
        listing.setCompany("Diver's Anonymous");
        listing.setCity("Ft. Myers");
        listing.setState("Florida");
        listing.setEmploymentType("Contract");
        listing.setSalary(40000);
        listing.setDatePosted(Instant.now());

        Listing savedListing = listingDao.save(listing);

        List<Listing> jobList = listingDao.findByCityAndStateAllIgnoreCase("ft. myers", "florida").get();

        assertEquals(savedListing, jobList.get(0));
    }

    @Test
    void getListingsByIndustry() {
        Listing listing = new Listing();
        listing.setListingName("Marine Biologist");
        listing.setIndustry("Environmental Science");
        listing.setCompany("Diver's Anonymous");
        listing.setCity("Ft. Myers");
        listing.setState("Florida");
        listing.setEmploymentType("Contract");
        listing.setSalary(40000);
        listing.setDatePosted(Instant.now());

        Listing savedListing = listingDao.save(listing);

        List<Listing> jobList = listingDao.findByIndustryIgnoreCase("environmental science").get();

        assertEquals(savedListing, jobList.get(0));
    }

    @Test
    void getListingsByEmploymentType() {
        Listing listing = new Listing();
        listing.setListingName("Marine Biologist");
        listing.setIndustry("Environmental Science");
        listing.setCompany("Diver's Anonymous");
        listing.setCity("Ft. Myers");
        listing.setState("Florida");
        listing.setEmploymentType("Contract");
        listing.setSalary(40000);
        listing.setDatePosted(Instant.now());

        Listing savedListing = listingDao.save(listing);

        List<Listing> jobList = listingDao.findByEmploymentTypeIgnoreCase("contract").get();

        assertEquals(savedListing, jobList.get(0));
    }

    @Test
    void getJobListsByDatePosted() {
        Listing listing = new Listing();
        listing.setListingName("Marine Biologist");
        listing.setIndustry("Environmental Science");
        listing.setCompany("Diver's Anonymous");
        listing.setCity("Ft. Myers");
        listing.setState("Florida");
        listing.setEmploymentType("Contract");
        listing.setSalary(40000);
        listing.setDatePosted(Instant.now());

        Listing savedListing = listingDao.save(listing);

        List<Listing> jobList = listingDao
                .findByDatePostedAfter(Instant.now().minus(1, ChronoUnit.DAYS)).get();

        assertEquals(savedListing, jobList.get(0));
    }

    @Test
    void createListingWithNullListingNameThrowsDataIntegrityViolationException() {
        Listing listing = new Listing();
        listing.setListingName(null);
        listing.setIndustry("Environmental Science");
        listing.setCompany("Diver's Anonymous");
        listing.setCity("Ft. Myers");
        listing.setState("Florida");
        listing.setEmploymentType("Contract");
        listing.setSalary(40000);
        listing.setDatePosted(Instant.now());

        assertThrows(DataIntegrityViolationException.class, () -> listingDao.save(listing));
    }

    @Test
    void createListingWithNegativeSalaryThrowsDataIntegrityViolationException() {
        Listing listing = new Listing();
        listing.setListingName("Marine Biologist");
        listing.setIndustry("Environmental Science");
        listing.setCompany("Diver's Anonymous");
        listing.setCity("Ft. Myers");
        listing.setState("Florida");
        listing.setEmploymentType("Contract");
        listing.setSalary(-40000);
        listing.setDatePosted(Instant.now());

        assertThrows(DataIntegrityViolationException.class, () -> listingDao.save(listing));
    }

    @Test
    void createListingWithoutDatePostedDefined() {
        Listing listing = new Listing();
        listing.setListingName("Marine Biologist");
        listing.setIndustry("Environmental Science");
        listing.setCompany("Diver's Anonymous");
        listing.setCity("Ft. Myers");
        listing.setState("Florida");
        listing.setEmploymentType("Contract");
        listing.setSalary(40000);

        Listing savedListing = listingDao.save(listing);

        assertNotNull(savedListing.getDatePosted());
    }

}
