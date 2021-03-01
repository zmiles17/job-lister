package com.talentpath.JobLister.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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
    private ApplicantAnswerKey id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("applicantId")
    @JoinColumn(name = "applicant_id")
    private Applicant applicant;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("questionId")
    @JoinColumn(name = "question_id")
    private Question question;

    private String answer;
}
