package com.talentpath.JobLister.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table
public class Listing implements Serializable {

    @Id
    @Setter(AccessLevel.PROTECTED)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "listing_id", unique = true)
    private Integer listingId;

    @Column(name = "listing_name", nullable = false)
    private String listingName;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(columnDefinition = "numeric CONSTRAINT salary_range CHECK (salary >= 0)")
    private Integer salary;

    @Column(name = "industry", nullable = false)
    private String industry;

    @Column(name = "employment_type", nullable = false)
    private String employmentType;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "date_posted")
    @CreationTimestamp
    private Instant datePosted;

    @OneToMany(mappedBy = "listing", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<Question> questions = new HashSet<>();

    @ManyToMany(mappedBy = "listings", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Applicant> applicants = new HashSet<>();
}
