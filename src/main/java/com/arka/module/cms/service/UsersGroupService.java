package com.arka.module.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.arka.module.cms.entity.PermissionMaster;
import com.arka.module.cms.entity.UsersGroup;
import com.arka.module.cms.repo.PermissionMasterRepo;
import com.arka.module.cms.repo.UsersGroupRepo;
@Service
public class UsersGroupService {

	
	@Autowired
	UsersGroupRepo userGroupRepo;
	
	public ResponseEntity <?> saveUserGroup( UsersGroup usersGroup ){
		UsersGroup saveUserGroup = userGroupRepo.save(usersGroup);
			return new ResponseEntity<>(saveUserGroup,HttpStatus.OK);
		

	}
	
	
	public ResponseEntity <?> listUserGroup(){
		List<UsersGroup> listUserGroup = userGroupRepo.findAll();
			return new ResponseEntity<>(listUserGroup,HttpStatus.OK);
		

	}
}
