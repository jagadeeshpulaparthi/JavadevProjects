package com.arka.module.cms.controller;

import java.security.Permissions;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arka.module.cms.entity.PermissionMaster;
import com.arka.module.cms.service.PermissionMasterService;

@RestController
public class PermissionMasterController {
	
	@Autowired
	PermissionMasterService permissionService;
	
	@PostMapping("/addPermission")
	public ResponseEntity <?> savePermission(@RequestBody PermissionMaster permissionMaster ){
		
		ResponseEntity<?> savePermission = permissionService.createPermission(permissionMaster);
			return new ResponseEntity<>(savePermission,HttpStatus.OK);
		

	}
	@PostMapping("/listPermission")
	public ResponseEntity <?> getPermissionList(){
		ResponseEntity<?> listPermission = permissionService.ListPermission();
			return new ResponseEntity<>(listPermission,HttpStatus.OK);
		

	}

}
