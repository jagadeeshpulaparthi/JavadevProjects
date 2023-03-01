package com.arka.module.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arka.module.cms.entity.ClientMaster;
import com.arka.module.cms.model.DTO.TenantOnboard;
import com.arka.module.cms.service.ClientMasterService;

@RestController
@CrossOrigin
public class ClientMasterController {
	
	@Autowired
	 ClientMasterService cmSer;
	
	
	
	
	@PostMapping("createCompany")	
	public ResponseEntity<?> createCompany(@RequestBody ClientMaster client){
		Object response = cmSer.createCompany(client);
		return new  ResponseEntity <>(response,HttpStatus.OK);
	}
	
	@GetMapping("getCompanies")
	public ResponseEntity<?> getCompanies(){
	Object companies = cmSer.getCompanies();
	return new  ResponseEntity <>(companies,HttpStatus.OK);
	}
	
	@PostMapping("deleteCompany")
	public ResponseEntity <?> deleteCompany(Long id){
		Object deleteCompany = cmSer.deleteCompany(id);
		return new ResponseEntity<>(deleteCompany,HttpStatus.OK);
	}
	
	@PostMapping("updateCompany")
	public ResponseEntity <?> updateCompany(Long id,String address,String modifiedBy,String name,String city,String pincode){
		Object upCompany = cmSer.updateCompany(id,address,modifiedBy,name,city,pincode);
		return new ResponseEntity<>(upCompany,HttpStatus.OK);
	}
	@PostMapping("onBoardTenant")
	public ResponseEntity <?> onBoardTenant(@RequestBody TenantOnboard tenant){
		Object ten = cmSer.onBoardTenant(tenant);
		return new ResponseEntity<>(ten,HttpStatus.OK);
	}
	
	@PostMapping("onBoardStore")
	public ResponseEntity <?> onBoardStore(@RequestBody TenantOnboard tenant){
		Object store = cmSer.onBoardStore(tenant);
		return new ResponseEntity<>(store,HttpStatus.OK);
	}
}
