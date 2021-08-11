package com.springwebfluxproject.springwebfluxproject.repository;

import com.springwebfluxproject.springwebfluxproject.dto.RecordDTO;
import com.springwebfluxproject.springwebfluxproject.entity.Record;
import reactor.core.publisher.Flux;

public interface RecordRepositoryExtended {
    public Flux<RecordDTO> getAllApprovedRecords();

}
