package com.talentpath.JobLister.services;

import com.talentpath.JobLister.exceptions.ResourceNotFoundException;
import com.talentpath.JobLister.models.*;
import com.talentpath.JobLister.persistence.AnswerDao;
import com.talentpath.JobLister.persistence.ApplicantDao;
import com.talentpath.JobLister.persistence.ListingDao;
import com.talentpath.JobLister.persistence.QuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class JobListingService {

    @Autowired
    private ListingDao listingDao;

    @Autowired
    private QuestionDao questionDao;

    @Autowired
    private ApplicantDao applicantDao;

    @Autowired
    private AnswerDao answerDao;

    public Listing saveListing(Listing listing) throws DataIntegrityViolationException {
        return listingDao.save(listing);
    }

    public Optional<Listing> getListingById(Integer listingId) {
        return listingDao.findById(listingId);
    }

    public List<Listing> getAllListings() {
        return listingDao.findAll();
    }

    public Optional<List<Listing>> getListingsByJobName(String jobName) {
        return listingDao.findByListingNameContainingIgnoreCase(jobName);
    }

    public Optional<List<Listing>> getListingsByCityAndState(String city, String state) {
        return listingDao.findByCityAndStateAllIgnoreCase(city, state);
    }

    public Optional<List<Listing>> getListingsByEmploymentType(String type) {
        return listingDao.findByEmploymentTypeIgnoreCase(type);
    }

    public Optional<List<Listing>> getListingsByIndustry(String industry) {
        return listingDao.findByIndustryContainingIgnoreCase(industry);
    }

    public Optional<List<Listing>> getListingsByCompany(String company) {
        return listingDao.findByCompanyContainingIgnoreCase(company);
    }

    public Optional<List<Listing>> getListingsBySalaryRange(Integer low, Integer high) {
        return listingDao.findBySalaryBetween(low, high);
    }

    @Transactional
    public Listing updateListing(Listing listing)
            throws ResourceNotFoundException, DataIntegrityViolationException {
        Listing currentListing = listingDao
                .findById(listing.getListingId())
                .orElseThrow(() -> new ResourceNotFoundException("Listing not found with listingId = " + listing.getListingId()));
//        listing.getApplicants().forEach(applicant -> applicantDao.delete(applicant));
        currentListing.setListingName(listing.getListingName());
        currentListing.setCity(listing.getCity());
        currentListing.setCompany(listing.getCompany());
        currentListing.setIndustry(listing.getIndustry());
        currentListing.setEmploymentType(listing.getEmploymentType());
        currentListing.setState(listing.getState());
        currentListing.setSalary(listing.getSalary());
//        currentListing.setQuestions(listing.getQuestions());
//        currentListing.setApplicants(new HashSet<>());
        return listingDao.save(currentListing);
    }

    public void deleteListing(Integer listingId) throws ResourceNotFoundException {
        Listing listing = listingDao.findById(listingId).orElseThrow(() -> new ResourceNotFoundException(
                "Listing not found with listingId = " + listingId
        ));
        listingDao.delete(listing);
    }

    public List<Question> saveQuestions(Integer listingId, List<Question> questions) {
        Optional<Listing> listing = getListingById(listingId);
        questions.forEach(q -> q.setListing(listing.orElseThrow()));
        return questionDao.saveAll(questions);
    }

    public List<Question> findQuestionsByListing(Integer listingId) {
        Optional<Listing> listing = getListingById(listingId);
        return questionDao.findQuestionsByListing(listing.orElseThrow());
    }

    public Question updateQuestion(Integer questionId, Question question) {
        Question currQuestion = questionDao.findById(questionId).orElseThrow();
        currQuestion.setQuestion(question.getQuestion());
        return questionDao.save(currQuestion);
    }

    public void deleteQuestion(Integer questionId) {
        questionDao.deleteById(questionId);
    }

    @Transactional
    public List<Answer> saveAnswers(Integer listingId, List<Answer> answers) {
        Listing listing = getListingById(listingId).orElseThrow();
        Applicant applicant = answers.get(0).getApplicant();
        applicant.getListings().add(listing);
        final Applicant applicantFromDb = applicantDao.findByApplicantNameAndPhoneNumberAndEmail(
                applicant.getApplicantName(), applicant.getPhoneNumber(), applicant.getEmail()
        ).orElse(applicantDao.save(applicant));



        for (Answer ans : answers) {
            ans.setApplicant(applicantFromDb);
            ans.getId().setApplicantId(applicantFromDb.getApplicantId());
            ans.getId().setQuestionId(ans.getQuestion().getQuestionId());
        }

        return answerDao.saveAll(answers);
    }

    public Applicant saveApplicant(Applicant applicant) {
        return applicantDao.save(applicant);
    }

    public List<Answer> getAllAnswers() {
        return answerDao.findAll();
    }

    public List<Applicant> getApplicants() {
        return applicantDao.findAll();
    }

    public Optional<List<Listing>> getListingsByDatePostedAfter(Integer daysAgo) {
        return listingDao.findByDatePostedAfter(Instant.now().minus(daysAgo, ChronoUnit.DAYS));
    }

    public Optional<Question> findQuestionById(Integer questionId) {
        return questionDao.findById(questionId);
    }
}
