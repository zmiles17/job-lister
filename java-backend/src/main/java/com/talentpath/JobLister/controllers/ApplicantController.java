package com.talentpath.JobLister.controllers;

import com.talentpath.JobLister.models.Applicant;
import com.talentpath.JobLister.services.JobListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicantController {

    @Autowired
    JobListingService service;

    @PostMapping("/applicant")
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
