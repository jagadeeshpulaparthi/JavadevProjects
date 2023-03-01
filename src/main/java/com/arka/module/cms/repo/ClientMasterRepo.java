package com.arka.module.cms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arka.module.cms.entity.ClientMaster;

@Repository
public interface ClientMasterRepo extends JpaRepository<ClientMaster, Long>{
	
	List<ClientMaster> findByMarkForDeletion(int i);
	List<ClientMaster> findByClientId(Long id);
	ClientMaster findByNameAndCreatedBy(String name,String crBy);

}
