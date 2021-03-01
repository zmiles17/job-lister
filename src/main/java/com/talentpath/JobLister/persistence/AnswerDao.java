package com.talentpath.JobLister.persistence;

import com.talentpath.JobLister.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerDao extends JpaRepository<Answer, Integer> {
}
