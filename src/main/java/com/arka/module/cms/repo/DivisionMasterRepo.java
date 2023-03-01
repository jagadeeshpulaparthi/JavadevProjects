package com.arka.module.cms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arka.module.cms.entity.DivisionMaster;


@Repository
public interface DivisionMasterRepo extends JpaRepository<DivisionMaster, Long>{
	
	List<DivisionMaster> findByMarkForDeletion(int del);
	List<DivisionMaster> findByDivisionId(Long id);
	@Query(value = "select cast(clone_schema (?1,?2) as text)",nativeQuery = true)
	String createSchema(String source,String destination);
}
