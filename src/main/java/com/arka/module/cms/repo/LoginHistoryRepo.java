package com.arka.module.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arka.module.cms.entity.LoginHistory;

@Repository
public interface LoginHistoryRepo extends JpaRepository<LoginHistory, Long>{
	
	LoginHistory findBySessionId(String id);

}
