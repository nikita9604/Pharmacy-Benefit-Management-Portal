package com.springwebfluxproject.springwebfluxproject.repository;

import com.springwebfluxproject.springwebfluxproject.entity.Pharmacy;
import reactor.core.publisher.Flux;

public interface PharmacyRepositoryExtended {
    public Flux<Pharmacy> getAllPharmacyWithDrugName(String dname);
    public Flux<Pharmacy> getAllPharmacyWithDrugNameInACity(String dname,String city);

}
