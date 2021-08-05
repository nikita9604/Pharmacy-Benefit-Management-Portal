package com.pbm.springbootpbm.repository;

import com.pbm.springbootpbm.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DrugRepository extends JpaRepository<Drug,Integer> {
    @Query("select new java.lang.Boolean(count(*) > 0) from Drug where dname = ?1")
    Boolean hasDrugname(String dname);
}
