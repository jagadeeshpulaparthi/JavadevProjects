package com.arka.module.cms.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arka.module.cms.entity.ContentCategory;
import com.arka.module.cms.entity.ContentDepartment;
import com.arka.module.cms.exception.ResourceNotFoundException;
import com.arka.module.cms.repo.ContentDepartmentRepo;
import com.arka.module.cms.service.ContentDepartmentService;
import com.arka.module.cms.utils.Response;

@SuppressWarnings({"unused"})
@RestController
@CrossOrigin
public class ContentDepartmentController {
	private static final Logger logger = LogManager.getLogger(ContentDepartmentController.class);
	
	@Autowired
	ContentDepartmentService deptSer;
	
	@Autowired
	ContentDepartmentRepo deptRepo;
	
	Response response ;

	
	
	
	@PostMapping(path = "addDepartment")
	
	 public  ResponseEntity<?> addDepartment(@RequestBody ContentDepartment docDept) {
//		String name = docDept.getDeptName();
//		boolean existsByDept = deptRepo.existsByDeptName(name);
//		if(existsByDept ) {
//			response = new Response(200,"Already Exists");
//			return new ResponseEntity<>( response,HttpStatus.OK);
//
//		}
//		else {
			ContentDepartment save = deptRepo.save(docDept);
			return new ResponseEntity<>( save,HttpStatus.OK);

//		}

		
	  }
	@PostMapping(path = "getDocDeptActive")
	
	 public  ResponseEntity<?> getDocTypeActive( @RequestBody ContentDepartment docDept ) throws ResourceNotFoundException  {
	   String oId = docDept.getOrganizationId();
	    		  List<ContentDepartment> findByEmail = deptRepo.findByUserIdAndMarkForDeletionAndOrganizationId(docDept.getUserId(),docDept.getMarkForDeletion(),oId);
	 	    		 return new ResponseEntity<>(findByEmail, HttpStatus.OK);
	 	   }
	@PostMapping(path = "updateDepartment")
	  public ResponseEntity<?> updateDepartment( @RequestBody ContentDepartment docDept) {
	    List<ContentDepartment> resultList = deptRepo.findByUserIdAndDeptId(docDept.getUserId(),docDept.getDeptId());
	    System.out.println("data--->" + resultList);
	    if (!resultList.isEmpty()) {
	    	ContentDepartment cat = resultList.get(0); 
	    	cat.setDeptName(docDept.getDeptName());
	    	cat.setModifiedBy(docDept.getModifiedBy());
	      return new ResponseEntity<>(deptRepo.save(cat), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>("Data Not Updated",HttpStatus.NOT_FOUND);
	    }
	  }
	
	@PostMapping(path = "deleteDepartment")
	  public ResponseEntity<?> deleteDepartment( @RequestBody ContentDepartment docDept) {
	    List<ContentDepartment> resultList = deptRepo.findByDeptId(docDept.getDeptId());
	    System.out.println("data--->" + resultList);
	    if (!resultList.isEmpty()) {
	    	ContentDepartment cat = resultList.get(0); 
	    	cat.setMarkForDeletion(docDept.getMarkForDeletion());
	  		cat.setModifiedBy(docDept.getModifiedBy());
	  		deptRepo.save(cat);
	  		response = new Response (200,"Deleted Successfully",null);

	    } else {
	  		response = new Response (204,"Failed to delete",null);

	    }
    	return new ResponseEntity<>(response,HttpStatus.OK);

	  }

	
	
	}
