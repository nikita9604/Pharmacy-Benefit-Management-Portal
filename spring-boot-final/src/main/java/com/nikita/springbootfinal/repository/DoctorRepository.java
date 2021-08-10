package com.nikita.springbootfinal.repository;

import com.nikita.springbootfinal.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    // Q3 - Match of Doctor Name for Prescription (User Dashboard Page)
    @Query("select new java.lang.Boolean(count(*) > 0) from Doctor where docname = ?1")
    Boolean hasDocname(String docname);
}
