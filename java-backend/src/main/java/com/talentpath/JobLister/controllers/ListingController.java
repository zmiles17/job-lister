package com.talentpath.JobLister.controllers;

import com.talentpath.JobLister.exceptions.ResourceNotFoundException;
import com.talentpath.JobLister.models.Listing;
import com.talentpath.JobLister.services.JobListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/listings")
public class ListingController {

    @Autowired
    private JobListingService jobListingService;

    @PostMapping
    public ResponseEntity<Listing> saveListing(@RequestBody Listing listing)
            throws DataIntegrityViolationException {
            return new ResponseEntity<>(jobListingService.saveListing(listing),
                    HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Listing>> getAllListings() {
        try {
            return new ResponseEntity<>(jobListingService.getAllListings(),
                    HttpStatus.OK);
        } catch (Exception e) {
            // TODO: Create custom exception
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{listingId}")
    public ResponseEntity<Listing> getListingById(@PathVariable Integer listingId)
            throws ResourceNotFoundException {
            return new ResponseEntity<>(jobListingService
                    .getListingById(listingId)
                    .orElseThrow(() -> new ResourceNotFoundException("Listing not found for listingId = "
                            + listingId)), HttpStatus.OK);
    }

    @GetMapping("/job/{name}")
    public ResponseEntity<List<Listing>> getListingByJobName(@PathVariable("name") String jobName)
            throws ResourceNotFoundException {
            return new ResponseEntity<>(jobListingService
                    .getListingsByJobName(jobName)
                    .orElseThrow(() -> new ResourceNotFoundException("No listing names found matching or containing \""
                            + jobName + "\"")), HttpStatus.OK);
    }

    @GetMapping("/{city}/{state}")
    public ResponseEntity<List<Listing>> getListingByCityAndState(@PathVariable String city,
                                                                  @PathVariable String state)
            throws ResourceNotFoundException {
            return new ResponseEntity<>(jobListingService
                    .getListingsByCityAndState(city, state)
                    .orElseThrow(() -> new ResourceNotFoundException("No listings found in city/state: "
                            + city + ", " + state)), HttpStatus.OK);
    }

    @GetMapping("/employment/{type}")
    public ResponseEntity<List<Listing>> getListingByEmploymentType(@PathVariable String type)
            throws ResourceNotFoundException {
            return new ResponseEntity<>(jobListingService
                    .getListingsByEmploymentType(type)
                    .orElseThrow(() -> new ResourceNotFoundException("No listings found for employment type = "
                            + type)), HttpStatus.OK);
    }

    @GetMapping("/industry/{industry}")
    public ResponseEntity<List<Listing>> getListingsByIndustry(@PathVariable String industry)
            throws ResourceNotFoundException {
            return new ResponseEntity<>(jobListingService
                    .getListingsByIndustry(industry)
                    .orElseThrow(() -> new ResourceNotFoundException("No listings found for industry = " + industry)),
                    HttpStatus.OK);
    }

    @GetMapping("/company/{company}")
    public ResponseEntity<List<Listing>> getListingsByCompany(@PathVariable String company)
            throws ResourceNotFoundException {
            return new ResponseEntity<>(jobListingService
                    .getListingsByCompany(company)
                    .orElseThrow(() -> new ResourceNotFoundException("No listings found for company = " + company)),
                    HttpStatus.OK);
    }

    @GetMapping("/salary/{low}/{high}")
    public ResponseEntity<List<Listing>> getListingsBySalaryRange(@PathVariable Integer low,
                                                                  @PathVariable Integer high)
            throws ResourceNotFoundException {
            return new ResponseEntity<>(jobListingService
                    .getListingsBySalaryRange(low, high)
                    .orElseThrow(() -> new ResourceNotFoundException("No listings found in the salary range of " +
                            low + "-" + high)), HttpStatus.OK);
    }

    @GetMapping("/datePosted/{daysAgo}")
    public ResponseEntity<List<Listing>> getListingsPostedAfter(@PathVariable Integer daysAgo)
            throws ResourceNotFoundException {
        return new ResponseEntity<>(jobListingService
                .getListingsByDatePostedAfter(daysAgo)
                .orElseThrow(() -> new ResourceNotFoundException("No listings posted in the past "
                        + daysAgo + " days")), HttpStatus.OK);
    }

    @PutMapping("/{listingId}")
    public ResponseEntity<Listing> updateListing(@PathVariable Integer listingId,
                                                 @RequestBody Listing listing)
            throws DataIntegrityViolationException, ResourceNotFoundException {
            return new ResponseEntity<>(jobListingService
                    .updateListing(listingId, listing), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{listingId}")
    public ResponseEntity<HttpStatus> deleteListing(@PathVariable Integer listingId)
            throws ResourceNotFoundException {
        jobListingService.deleteListing(listingId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
