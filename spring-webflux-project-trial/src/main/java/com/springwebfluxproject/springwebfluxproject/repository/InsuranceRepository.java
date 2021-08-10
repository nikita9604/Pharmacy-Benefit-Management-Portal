package com.springwebfluxproject.springwebfluxproject.repository;


import com.springwebfluxproject.springwebfluxproject.entity.Insurance;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface InsuranceRepository extends ReactiveCrudRepository<Insurance,Integer> {
    @Query("select count(*) > 0 from Insurance where inid=:inid")
    Mono<Boolean> hasInid(int inid);

}
