package com.springwebfluxproject.springwebfluxproject.repository;

import com.springwebfluxproject.springwebfluxproject.entity.Pharmacy;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class DrugRepositoryExtendedImpl implements DrugRepositoryExtended {
    private DatabaseClient client;
    public DrugRepositoryExtendedImpl(DatabaseClient client) {

        this.client = client;

    }
    public Mono<Boolean> checkIfDrugCoveredByInsurance(String dname, String plan)
    {
        String query="select count(*) > 0 as check from insurance natural join drug natural join insurancedrug where dname=:dname and plan=:plan";
        return client.sql(query).bind("dname", dname).bind("plan", plan).map(row -> new Boolean(row.get("check", Boolean.class))).one();

    }


}
