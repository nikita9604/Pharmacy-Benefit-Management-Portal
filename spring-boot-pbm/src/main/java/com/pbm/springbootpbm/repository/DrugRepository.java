package com.pbm.springbootpbm.repository;

import com.pbm.springbootpbm.entity.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<Drug,Integer> {
}
