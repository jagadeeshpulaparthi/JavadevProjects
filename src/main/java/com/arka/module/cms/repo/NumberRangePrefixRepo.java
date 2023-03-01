package com.arka.module.cms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arka.module.cms.entity.NumberRangePrefix;


@Repository
public interface NumberRangePrefixRepo extends JpaRepository<NumberRangePrefix, Long>{
	
	boolean existsByPrefix(String prefix);
	List<NumberRangePrefix> findByUserId(Long id);

}
