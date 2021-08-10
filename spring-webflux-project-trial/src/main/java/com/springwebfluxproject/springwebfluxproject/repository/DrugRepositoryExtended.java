package com.springwebfluxproject.springwebfluxproject.repository;

import reactor.core.publisher.Mono;

public interface DrugRepositoryExtended {
    Mono<Boolean> checkIfDrugCoveredByInsurance(String dname, String plan);
}
