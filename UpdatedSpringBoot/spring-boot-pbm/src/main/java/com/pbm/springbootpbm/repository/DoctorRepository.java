package com.pbm.springbootpbm.repository;

import com.pbm.springbootpbm.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
    @Query("select new java.lang.Boolean(count(*) > 0) from Doctor where docname = ?")
    Boolean hasDocname(String docname);
}
