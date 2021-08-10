package com.springwebfluxproject.springwebfluxproject.repository;

import com.springwebfluxproject.springwebfluxproject.entity.Pharmacy;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class PharmacyRepositoryExtendedImpl implements PharmacyRepositoryExtended{
    private DatabaseClient client;
    public PharmacyRepositoryExtendedImpl(DatabaseClient client) {

        this.client = client;

    }
    public Flux<Pharmacy> getAllPharmacyWithDrugName(String dname)
    {
        String query="select * from pharmacy natural join drug natural join pharmacydrug where dname=:dname";
        Flux<Pharmacy> result = client.sql(query).bind("dname", dname).map(row -> new Pharmacy(row.get("phid", Integer.class),
                row.get("phname", String.class),row.get("city",String.class))).all();
        return result;
    }

    public Flux<Pharmacy> getAllPharmacyWithDrugNameInACity(String dname,String city)
    {
        String query="select * from pharmacy natural join drug natural join pharmacydrug where dname=:dname and city=:city";
        Flux<Pharmacy> result = client.sql(query).bind("dname", dname).bind("city",city.toUpperCase()).map(row -> new Pharmacy(row.get("phid", Integer.class),
                row.get("phname", String.class),row.get("city",String.class))).all();
        return result;
    }


}
