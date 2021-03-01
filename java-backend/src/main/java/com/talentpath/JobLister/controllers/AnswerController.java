package com.talentpath.JobLister.controllers;

import com.talentpath.JobLister.models.Answer;
import com.talentpath.JobLister.services.JobListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AnswerController {

    @Autowired
    private JobListingService service;

    @PostMapping("/answers/listing/{listingId}")
    public ResponseEntity saveAnswers(@PathVariable Integer listingId,
                                      @RequestBody List<Answer> answers) {
        ResponseEntity response;
        try {
            response = ResponseEntity.ok(service.saveAnswers(listingId, answers));
        } catch (Exception e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
        }
        return response;
    }

    @GetMapping("/answers")
    public ResponseEntity getAllAnswers() {
        ResponseEntity response;
        try {
            response = ResponseEntity.ok(service.getAllAnswers());
        } catch(Exception e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
        }
        return response;
    }
}
