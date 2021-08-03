package com.pbm.springbootpbm.repository;

import com.pbm.springbootpbm.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record,Integer> {
}
