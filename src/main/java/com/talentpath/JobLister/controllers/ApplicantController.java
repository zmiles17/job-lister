package com.talentpath.JobLister.controllers;

import com.talentpath.JobLister.models.Applicant;
import com.talentpath.JobLister.services.JobListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class ApplicantController {

    @Autowired
    JobListingService service;

    @PostMapping(value = "/applicant", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveApplicant(@RequestBody Applicant applicant) {
        ResponseEntity response;
        try {
            response = ResponseEntity.ok(service.saveApplicant(applicant));
        } catch (Exception e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
        }
        return response;
    }

    @GetMapping("/applicants")
    public ResponseEntity getApplicants() {
        ResponseEntity response;
        try {
            response = ResponseEntity.ok(service.getApplicants());
        } catch (Exception e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
        }
        return response;
    }
}
