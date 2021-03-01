package com.talentpath.JobLister.persistence;

import com.talentpath.JobLister.models.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicantDao extends JpaRepository<Applicant, Integer> {
}
