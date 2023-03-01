package com.arka.module.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arka.module.cms.entity.AuthGroupMaster;
import com.arka.module.cms.entity.UsersGroup;
import com.arka.module.cms.service.AuthGroupMasterService;
import com.arka.module.cms.service.UsersGroupService;

@RestController
public class AuthGroupMasterController {

	@Autowired
	AuthGroupMasterService authGroupMasterService;
	
	@PostMapping("/addAuthGroupMaster")
	public ResponseEntity <?> addAuthGroupMaster(@RequestBody AuthGroupMaster authGroupMaster ){
		
		ResponseEntity<?> addAuthGroupMaster = authGroupMasterService.saveAuthGroupMaster(authGroupMaster);
			return new ResponseEntity<>(addAuthGroupMaster,HttpStatus.OK);
		

	}
	@PostMapping("/listAuthGroupMaster")
	public ResponseEntity <?> listAuthGroupMaster(){
		ResponseEntity<?> listAuthGroupMaster = authGroupMasterService.listAuthGroupMaster();
			return new ResponseEntity<>(listAuthGroupMaster,HttpStatus.OK);
		

	}
}
