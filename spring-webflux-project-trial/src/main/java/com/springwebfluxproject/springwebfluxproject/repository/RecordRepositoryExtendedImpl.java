package com.springwebfluxproject.springwebfluxproject.repository;

import com.springwebfluxproject.springwebfluxproject.dto.RecordDTO;
import com.springwebfluxproject.springwebfluxproject.entity.Pharmacy;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;

public class RecordRepositoryExtendedImpl implements RecordRepositoryExtended{
    private DatabaseClient client;
    public RecordRepositoryExtendedImpl(DatabaseClient client) {

        this.client = client;

    }
    public Flux<RecordDTO> getAllApprovedRecords(){
        String query="select * from record natural join drug natural join patient where status=:status";
        String status="Prescription Confirmed";
        Flux<RecordDTO> result = client.sql(query).bind("status", status)
                .map(row -> new RecordDTO(row.get("rid",Integer.class),row.get("pid", Integer.class),row.get("did", Integer.class),
                row.get("docname", String.class),row.get("city",String.class),row.get("name", String.class),row.get("dname", String.class))).all();
        return result;
    }

}
