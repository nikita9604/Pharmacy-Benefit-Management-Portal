package com.springwebfluxproject.springwebfluxproject.repository;

import com.springwebfluxproject.springwebfluxproject.entity.Pharmacy;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PharmacyRepository extends ReactiveCrudRepository<Pharmacy,Integer> {
}
