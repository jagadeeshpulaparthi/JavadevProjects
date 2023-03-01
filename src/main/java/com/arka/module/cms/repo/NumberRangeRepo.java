package com.arka.module.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arka.module.cms.entity.NumberRange;

@Repository
public interface NumberRangeRepo extends JpaRepository<NumberRange, Long>{
	long countByType(String type);
}
