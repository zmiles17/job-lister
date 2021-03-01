package com.talentpath.JobLister.services;

import com.talentpath.JobLister.models.Listing;
import com.talentpath.JobLister.persistence.AnswerDao;
import com.talentpath.JobLister.persistence.ApplicantDao;
import com.talentpath.JobLister.persistence.ListingDao;
import com.talentpath.JobLister.persistence.QuestionDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class JobListingServiceTests {

    @InjectMocks
    JobListingService service;

    @Mock
    ListingDao listingDao;

    @Mock
    QuestionDao questionDao;

    @Mock
    ApplicantDao applicantDao;

    @Mock
    AnswerDao answerDao;

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

        service.saveListing(listing);

        verify(listingDao, times(1)).save(listing);
    }

    @Test
    void creatingListingWithNullRequiredFieldsShouldThrowDataIntegrityViolationException() {
        Listing listing = new Listing();
        listing.setListingName("Marine Biologist");
        listing.setIndustry("Environmental Science");
        listing.setCompany("Diver's Anonymous");
        listing.setCity("Ft. Myers");
        listing.setState("Florida");
        listing.setEmploymentType(null);
        listing.setSalary(40000);

        when(listingDao.save(listing)).thenThrow(DataIntegrityViolationException.class);

        assertThrows(DataIntegrityViolationException.class, () -> service.saveListing(listing));
    }

    @Test
    void getAllListings() {
        List<Listing> listings = new ArrayList<>();
        Listing listing = new Listing();
        listing.setListingName("Marine Biologist");
        listing.setIndustry("Environmental Science");
        listing.setCompany("Diver's Anonymous");
        listing.setCity("Ft. Myers");
        listing.setState("Florida");
        listing.setEmploymentType("Contract");
        listing.setSalary(40000);

        listings.add(listing);
        listings.add(listing);
        listings.add(listing);

        when(listingDao.findAll()).thenReturn(listings);

        List<Listing> allListings = service.getAllListings();

        assertEquals(3, allListings.size());
        verify(listingDao, times(1)).findAll();
    }

    @Test
    void getListingById() {
        Listing listing = new Listing(1, "Park Ranger", "Blue Ridge Park Patrol",
                45000, "Wildlife Preservation", "Full-Time",
                "Helen", "Georgia", Instant.now(), new HashSet<>(), new HashSet<>());
        when(listingDao.findById(1)).thenReturn(Optional.of(listing));
        Optional<Listing> listingWithId1 = service.getListingById(1);
        assertEquals(listing, listingWithId1.get());
        verify(listingDao, times(1)).findById(1);
    }

    @Test
    void getListingsByJobName() {
        Listing listing = new Listing(1, "Park Ranger", "Blue Ridge Park Patrol",
                45000, "Wildlife Preservation", "Full-Time",
                "Helen", "Georgia", Instant.now(), new HashSet<>(), new HashSet<>());

        when(listingDao.findByListingNameContainingIgnoreCase("park")).thenReturn(
                Optional.of(new ArrayList<>(){{ add(listing); }}));

        List<Listing> listingsWithJobNameContainingPark = service.getListingsByJobName("park").get();
        verify(listingDao, times(1)).findByListingNameContainingIgnoreCase("park");
        assertEquals(1, listingsWithJobNameContainingPark.size());
    }

    @Test
    void getListingsByCityAndState() {
        Listing listing = new Listing(1, "Park Ranger", "Blue Ridge Park Patrol",
                45000, "Wildlife Preservation", "Full-Time",
                "Helen", "Georgia", Instant.now(), new HashSet<>(), new HashSet<>());

        when(listingDao.findByCityAndStateAllIgnoreCase("helen", "georgia")).thenReturn(
                Optional.of(new ArrayList<>(){{ add(listing); }}));

        List<Listing> helenGeorgiaListings = service.getListingsByCityAndState("helen", "georgia").get();
        verify(listingDao, times(1)).findByCityAndStateAllIgnoreCase("helen", "georgia");
        assertEquals(1, helenGeorgiaListings.size());
    }

    @Test
    void getListingsByEmploymentType() {
        Listing listing = new Listing(1, "Park Ranger", "Blue Ridge Park Patrol",
                45000, "Wildlife Preservation", "Full-Time",
                "Helen", "Georgia", Instant.now(), new HashSet<>(), new HashSet<>());

        when(listingDao.findByEmploymentTypeIgnoreCase("full-time")).thenReturn(
                Optional.of(new ArrayList<>(){{ add(listing); }}));

        List<Listing> fullTimeJobs = service.getListingsByEmploymentType("full-time").get();

        verify(listingDao, times(1)).findByEmploymentTypeIgnoreCase("full-time");
        assertEquals(1, fullTimeJobs.size());
    }

    @Test
    void getListingsByIndustry() {
    }

    @Test
    void getListingsByCompany() {
    }

    @Test
    void getListingsBySalaryRange() {
    }

    @Test
    void updateListing() {
    }

    @Test
    void deleteListing() {
    }

    @Test
    void saveQuestions() {
    }

    @Test
    void findQuestionsByListing() {
    }

    @Test
    void updateQuestion() {
    }

    @Test
    void deleteQuestion() {
    }

    @Test
    void saveAnswers() {
    }

    @Test
    void saveApplicant() {
    }

    @Test
    void getAllAnswers() {
    }

    @Test
    void getApplicants() {
    }

}
