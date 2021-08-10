package com.nikita.springbootfinal.repository;

import com.nikita.springbootfinal.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
    // Q2 - Authentication for Login for user (Patient)
    //@Query("select new java.lang.Boolean(count(*) > 0) from Patient where name = ?1 and pass = ?2")
    //Boolean hasLogin(String name,Integer pass);
}
