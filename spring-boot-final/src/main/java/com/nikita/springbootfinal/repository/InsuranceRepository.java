package com.nikita.springbootfinal.repository;

import com.nikita.springbootfinal.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InsuranceRepository extends JpaRepository<Insurance,Integer> {
    // Q1 - Match of Insurance ID (User Sign Up Page)
    @Query("select new java.lang.Boolean(count(*) > 0) from Insurance where inid = ?1")
    Boolean hasInid(int inid);
}
