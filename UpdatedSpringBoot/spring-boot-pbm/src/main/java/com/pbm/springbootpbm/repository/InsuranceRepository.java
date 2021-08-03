package com.pbm.springbootpbm.repository;

import com.pbm.springbootpbm.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InsuranceRepository extends JpaRepository<Insurance,Integer> {
    @Query("select new java.lang.Boolean(count(*) > 0) from Insurance where inid = ?")
    Boolean hasInid(int inid);
}
