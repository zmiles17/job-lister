package com.talentpath.JobLister.dao;

import com.talentpath.JobLister.models.Listing;
import com.talentpath.JobLister.persistence.ListingDao;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile({"serviceTest"})
public class ListingInMemDao implements ListingDao {

    private List<Listing> listings = new ArrayList<>();


    @Override
    public Optional<List<Listing>> findByListingNameContainingIgnoreCase(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Listing>> findByCityAndStateAllIgnoreCase(String city, String state) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Listing>> findByEmploymentTypeIgnoreCase(String type) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Listing>> findByIndustryIgnoreCase(String industry) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Listing>> findByCompanyContainingIgnoreCase(String company) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Listing>> findBySalaryBetween(Integer low, Integer high) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Listing>> findByDatePostedAfter(Instant instant) {
        return Optional.empty();
    }

    @Override
    public List<Listing> findAll() {
        return listings;
    }

    @Override
    public List<Listing> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Listing> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Listing> findAllById(Iterable<Integer> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Listing listing) {

    }

    @Override
    public void deleteAll(Iterable<? extends Listing> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public <S extends Listing> S save(S s) {
        return null;
    }

    @Override
    public <S extends Listing> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Listing> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Listing> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Listing> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Listing getOne(Integer integer) {
        return null;
    }

    @Override
    public <S extends Listing> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Listing> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Listing> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Listing> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Listing> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Listing> boolean exists(Example<S> example) {
        return false;
    }
}
