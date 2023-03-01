package com.arka.module.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arka.module.cms.model.DTO.DivisionMasterDTO;
import com.arka.module.cms.service.DivisionMasterService;

@RestController
@CrossOrigin
public class DivisionMasterController {
	
	@Autowired
	DivisionMasterService dmSer;
	
	@PostMapping("addDivision")
	public ResponseEntity <?>  addDivision(@RequestBody DivisionMasterDTO division){
		Object addDivision = dmSer.addDivision(division);
		return new ResponseEntity<>(addDivision,HttpStatus.OK);
	}
	
	@GetMapping("getDivisions")
	public ResponseEntity <?>  getDivisions(){
		Object divisions = dmSer.getDivisions();
		return new ResponseEntity<>(divisions,HttpStatus.OK);
	}
	
	@PostMapping("deleteDivision")
	public ResponseEntity <?> deleteDivision(Long id){
		Object deleteDivision = dmSer.deleteDivision(id);
		return new ResponseEntity<>(deleteDivision,HttpStatus.OK);
	}
	
	@PostMapping("updateDivision")
	public ResponseEntity <?> updateDivision(Long id,String clientId,String divisionName,String modifiedBy){
		Object upDivision = dmSer.updateDivision(id,clientId,divisionName,modifiedBy);
		return new ResponseEntity<>(upDivision,HttpStatus.OK);
	}
	
	
}

