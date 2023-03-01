package com.arka.module.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.arka.module.cms.entity.Folder;
import com.arka.module.cms.entity.PermissionMaster;
import com.arka.module.cms.repo.PermissionMasterRepo;
import com.arka.module.cms.utils.Response;

@Service
public class PermissionMasterService {
	
	@Autowired
	PermissionMasterRepo permissionRepo;
	
	public ResponseEntity <?> createPermission( PermissionMaster permissionMaster ){
		PermissionMaster savePermission = permissionRepo.save(permissionMaster);
			return new ResponseEntity<>(savePermission,HttpStatus.OK);
		

	}
	
	
	public ResponseEntity <?> ListPermission(){
		List<PermissionMaster> listPermission = permissionRepo.findAll();
			return new ResponseEntity<>(listPermission,HttpStatus.OK);
		

	}

}
