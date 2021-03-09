package com.talentpath.JobLister.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "answers"})
@Table
public class Question implements Serializable {

    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "question_id", unique = true)
    private Integer questionId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "fk_listing_id", referencedColumnName = "listing_id")
    @JsonBackReference
    private Listing listing;

    @Column(nullable = false)
    private String question;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Answer> answers = new HashSet<>();

}
