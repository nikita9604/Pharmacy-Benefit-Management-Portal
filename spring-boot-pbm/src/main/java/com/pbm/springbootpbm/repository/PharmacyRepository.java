package com.pbm.springbootpbm.repository;

import com.pbm.springbootpbm.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy,Integer> {
}
