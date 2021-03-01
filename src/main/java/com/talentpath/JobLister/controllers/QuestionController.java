package com.talentpath.JobLister.controllers;

import com.talentpath.JobLister.models.Question;
import com.talentpath.JobLister.services.JobListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private JobListingService service;

    @PostMapping("/listing/{listingId}")
    public ResponseEntity saveQuestions(@PathVariable Integer listingId,
                                        @RequestBody List<Question> questions) {
        ResponseEntity response;
        try {
            response = ResponseEntity.ok(service.saveQuestions(listingId, questions));
        } catch (Exception e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
        }
        return response;
    }

    @GetMapping("/listing/{listingId}")
    public ResponseEntity findQuestionsByListing(@PathVariable Integer listingId) {
        ResponseEntity response;
        try {
            response = ResponseEntity.ok(service.findQuestionsByListing(listingId));
        } catch (Exception e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
        }
        return response;
    }

    @PutMapping("/{questionId}")
    public ResponseEntity updateQuestion(@PathVariable Integer questionId, @RequestBody Question question) {
        ResponseEntity response;
        try {
            response = ResponseEntity.ok(service.updateQuestion(questionId, question));
        } catch(Exception e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
        }
        return response;
    }

    @DeleteMapping("/{questionId}")
    public ResponseEntity deleteQuestion(@PathVariable Integer questionId) {
        ResponseEntity response;
        try {
            service.deleteQuestion(questionId);
            response = ResponseEntity.accepted().build();
        } catch (Exception e) {
            response = ResponseEntity.badRequest().body(e.getMessage());
        }
        return response;
    }

}
