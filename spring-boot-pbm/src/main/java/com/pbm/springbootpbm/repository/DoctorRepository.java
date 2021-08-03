package com.pbm.springbootpbm.repository;

import com.pbm.springbootpbm.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {
}
