package com.talentpath.JobLister.persistence;

import com.talentpath.JobLister.models.Answer;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile({"production"})
public interface AnswerDao extends JpaRepository<Answer, Integer> {
}
