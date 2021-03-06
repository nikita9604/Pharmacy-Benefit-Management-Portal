package com.springwebfluxproject.springwebfluxproject.repository;

import com.springwebfluxproject.springwebfluxproject.entity.Record;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface RecordRepository extends ReactiveCrudRepository<Record,Integer>,RecordRepositoryExtended{
    @Query("update record set phid =:phid,status=:status where rid=:rid")
    Flux<Record> updateRecords(int rid,int phid,String status);

    @Query("Select * from record where pid=:pid")
    Flux<Record> getAllRecordsOfAPatient(int pid);
}
