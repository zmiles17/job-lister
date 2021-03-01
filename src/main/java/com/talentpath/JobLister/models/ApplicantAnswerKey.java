package com.talentpath.JobLister.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicantAnswerKey implements Serializable {

    @Column(name = "question_id")
    private Integer questionId;

    @Column(name = "applicant_id")
    private Integer applicantId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApplicantAnswerKey that = (ApplicantAnswerKey) o;
        return questionId.equals(that.questionId) && applicantId.equals(that.applicantId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId, applicantId);
    }
}
