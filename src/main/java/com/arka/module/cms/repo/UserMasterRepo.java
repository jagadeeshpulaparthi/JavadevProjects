package com.arka.module.cms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arka.module.cms.entity.UserMaster;

@Repository

public interface UserMasterRepo extends JpaRepository <UserMaster, Integer> {
	UserMaster findByEmailId(String email);
	UserMaster findByEmailIdAndOrganizationId(String email,String oId);
	UserMaster findByEmailIdAndPassword(String email,String password);
	UserMaster findByUserId(Long id);

}
