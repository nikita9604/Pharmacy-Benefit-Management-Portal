package com.springwebfluxproject.springwebfluxproject.repository;


import com.springwebfluxproject.springwebfluxproject.entity.Doctor;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface DoctorRepository extends ReactiveCrudRepository<Doctor,Integer> {
    @Query("select count(*) > 0 from Doctor where docname = :docname")
    Mono<Boolean> hasDocname(String docname);
}
