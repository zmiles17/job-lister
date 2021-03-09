package com.talentpath.JobLister.persistence;

import com.talentpath.JobLister.models.Applicant;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Profile({"production"})
public interface ApplicantDao extends JpaRepository<Applicant, Integer> {
    Optional<Applicant> findByApplicantNameAndPhoneNumberAndEmail(String applicantName, String phoneNumber, String email);
}
