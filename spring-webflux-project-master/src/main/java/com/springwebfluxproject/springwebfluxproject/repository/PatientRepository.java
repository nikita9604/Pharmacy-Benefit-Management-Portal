package com.springwebfluxproject.springwebfluxproject.repository;


import com.springwebfluxproject.springwebfluxproject.entity.Patient;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PatientRepository extends ReactiveCrudRepository<Patient,Integer> {
}
