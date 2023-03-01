package com.arka.module.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.arka.module.cms.entity.AuthGroupMaster;
import com.arka.module.cms.entity.UsersGroup;
import com.arka.module.cms.repo.AuthGroupMasterRepo;
import com.arka.module.cms.repo.UsersGroupRepo;
@Service
public class AuthGroupMasterService {

	@Autowired
	AuthGroupMasterRepo authGroupMasterRepo;
	
	public ResponseEntity <?> saveAuthGroupMaster( AuthGroupMaster authGroupMaster ){
		AuthGroupMaster saveAuthGroupMaster = authGroupMasterRepo.save(authGroupMaster);
			return new ResponseEntity<>(saveAuthGroupMaster,HttpStatus.OK);
		

	}
	
	
	public ResponseEntity <?> listAuthGroupMaster(){
		List<AuthGroupMaster> listAuthGroupMaster = authGroupMasterRepo.findAll();
			return new ResponseEntity<>(listAuthGroupMaster,HttpStatus.OK);
		

	}
}
