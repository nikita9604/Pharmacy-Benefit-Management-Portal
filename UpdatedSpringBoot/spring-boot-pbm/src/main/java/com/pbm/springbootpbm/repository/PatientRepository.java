package com.pbm.springbootpbm.repository;

import com.pbm.springbootpbm.entity.Patient;
import com.pbm.springbootpbm.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
    // Q3 - Authentication for Login for user (Patient)
    @Query("select new java.lang.Boolean(count(*) > 0) from Patient where name = ?1 and pass = ?2")
    Boolean hasLogin(String name,int pass);
}
