package com.talentpath.JobLister.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "applicant", "question"}, allowSetters = true)
@Table
public class Answer implements Serializable {

    @EmbeddedId
    private ApplicantAnswerKey id = new ApplicantAnswerKey();

    @ManyToOne
    @MapsId("applicantId")
    @JoinColumn(name = "fk_applicant_id", referencedColumnName = "applicant_id")
    private Applicant applicant;

    @ManyToOne
    @MapsId("questionId")
    @JoinColumn(name = "fk_question_id", referencedColumnName = "question_id")
    private Question question;

    private String answer;
}
