package com.arka.module.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arka.module.cms.entity.PermissionMaster;
import com.arka.module.cms.entity.UsersGroup;
import com.arka.module.cms.service.PermissionMasterService;
import com.arka.module.cms.service.UsersGroupService;
@RestController
public class UsersGroupController {

	@Autowired
	UsersGroupService usersGroupService;
	
	@PostMapping("/addUserGroup")
	public ResponseEntity <?> addUserGroup(@RequestBody UsersGroup usersGroup ){
		
		ResponseEntity<?> addUserGroup = usersGroupService.saveUserGroup(usersGroup);
			return new ResponseEntity<>(addUserGroup,HttpStatus.OK);
		

	}
	@PostMapping("/listUserGroup")
	public ResponseEntity <?> listUserGroup(){
		ResponseEntity<?> listUserGroup = usersGroupService.listUserGroup();
			return new ResponseEntity<>(listUserGroup,HttpStatus.OK);
		

	}

}
