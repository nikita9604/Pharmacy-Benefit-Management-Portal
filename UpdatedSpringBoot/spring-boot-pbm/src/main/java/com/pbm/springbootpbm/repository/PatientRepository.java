package com.pbm.springbootpbm.repository;

import com.pbm.springbootpbm.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
}
