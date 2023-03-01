package com.arka.module.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.arka.module.cms.entity.DivisionMaster;
import com.arka.module.cms.model.DTO.DivisionMasterDTO;
import com.arka.module.cms.repo.DivisionMasterRepo;
import com.arka.module.cms.utils.Response;

@Service
public class DivisionMasterService {
	
	@Autowired
	DivisionMasterRepo dmRepo;
	
	Response res;
	
	public Object addDivision(@RequestBody DivisionMasterDTO  division) {
		String organizationId = division.getClientId() + "_" +division.getDivisionName();
		DivisionMaster  addDiv =  new DivisionMaster();
		addDiv.setClientId(division.getClientId());
		addDiv.setDivisionName(division.getDivisionName());
		addDiv.setCreatedRole(division.getCreatedRole());
		addDiv.setCreatedBy(division.getCreatedBy());
		addDiv.setOrganizationId(organizationId);
		DivisionMaster div = dmRepo.save(addDiv);
		//String createSchema = dmRepo.createSchema("public", organizationId);	
		//System.out.println("createSchema------->"+ createSchema);
		if(div != null) {
			return div;		
		}else {
			res = new Response (204,"Failed to create");
			return res;
		}
	}
		
	public Object getDivisions() {
	List<DivisionMaster> findAll = dmRepo.findByMarkForDeletion(0);
	if (findAll.isEmpty()) {
		res = new Response (204,"List is Empty");
		return res;
	}else {
		return findAll;
	}
		
	}
	
	public Object deleteDivision(Long id ) {
	List<DivisionMaster> findById = dmRepo.findByDivisionId(id);
	if(findById != null ) {
		DivisionMaster div = findById.get(0);
		div.setMarkForDeletion(1); 
		DivisionMaster save = dmRepo.save(div);
		res = new Response(200, "Deleted Sucessfully");
	}else {
		res = new Response(204, "Failed to delete");
	}
	return res;
	}
		
	public Object updateDivision(Long id,String clientId,String divisionName,String modifiedBy) {
		String organizationId = clientId + "_" +divisionName;
		List<DivisionMaster> findById = dmRepo.findByDivisionId(id);

		DivisionMaster  addDiv = findById.get(0);
		addDiv.setClientId(clientId);
		addDiv.setDivisionName(divisionName);	
		addDiv.setCreatedBy(modifiedBy);
		addDiv.setOrganizationId(organizationId);
		DivisionMaster div = dmRepo.save(addDiv);
		if(div != null) {
			return div;		
		}else {
			res = new Response (204,"Failed to Update");
			return res;
		}
		
	}
	

}
