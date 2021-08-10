package com.nikita.springbootfinal.repository;

import com.nikita.springbootfinal.entity.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacyRepository extends JpaRepository<Pharmacy,Integer> {
}
