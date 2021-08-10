package com.nikita.springbootfinal.repository;

import com.nikita.springbootfinal.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record,Integer> {
    @Query("SELECT r FROM Record r WHERE r.status='pending'")
    public List<Record> getPendingRecord();
}
