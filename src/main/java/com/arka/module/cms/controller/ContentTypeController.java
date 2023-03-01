package com.arka.module.cms.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arka.module.cms.dto.ContentTypeDto;
import com.arka.module.cms.entity.ContentType;
import com.arka.module.cms.exception.ResourceNotFoundException;
import com.arka.module.cms.repo.ContentTypeRepo;
import com.arka.module.cms.service.ContentTypeService;
import com.arka.module.cms.utils.Response;

@SuppressWarnings({"unused"})
@RestController
@CrossOrigin
public class ContentTypeController {
	private static final Logger logger = LogManager.getLogger(ContentTypeController.class);

	@Autowired
	ContentTypeService typeSer;
	
	@Autowired
	ContentTypeRepo typeRepo;
	Response response ;

	
	@PostMapping(path = "addType")
	
	 public  ResponseEntity<?> addType(@RequestBody ContentType type) {
		String name = type.getType();
		boolean existsByDept = typeRepo.existsByType(name);
		if(existsByDept ) {
			response = new Response (409,"Already exists");
			return new ResponseEntity<>( response,HttpStatus.OK);
		}
		else {
			ContentType save = typeRepo.save(type);
			return new ResponseEntity<>(save, HttpStatus.OK);
		}
		
	  }
	@PostMapping(path = "getTypeActive")
	
	 public  ResponseEntity<?> getTypeActive( @RequestBody ContentType type ) throws ResourceNotFoundException  {
	   
	    		  List<ContentType> findByEmail = typeRepo.findByUserIdAndMarkForDeletionAndOrganizationIdOrderByCreatedOnDesc(type.getUserId(),type.getMarkForDeletion(),type.getOrganizationId());
	    		  
	 	    		 return new ResponseEntity<>(findByEmail, HttpStatus.OK);
	 	   }
	@PostMapping(path = "updateType")
	  public ResponseEntity<?> updateType( @RequestBody ContentType type) {
	    List<ContentType> resultList = typeRepo.findByTypeId(type.getTypeId());
	    System.out.println("data--->" + resultList);
	    if (!resultList.isEmpty()) {
	    	ContentType cat = resultList.get(0); 
	    	cat.setType(type.getType());
	    	cat.setModifiedBy(type.getModifiedBy());
	      return new ResponseEntity<>(typeRepo.save(cat), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>("Data Not Updated",HttpStatus.NOT_FOUND);
	    }
	  }
	@GetMapping("getType")
	public  ResponseEntity<?> getType(Long userId,Long categoryId,Long departmentId ) {
		   
		  List<ContentTypeDto> findByEmail = typeRepo.findByUserIdAndDepartmentIdAndCategoryIdOrderByCreatedOnDesc(userId,categoryId,departmentId);
		  if(findByEmail.isEmpty()) {
			  response = new Response (204,"No content");
			return new ResponseEntity<>(response, HttpStatus.OK);

		  }
   		 return new ResponseEntity<>(findByEmail, HttpStatus.OK);
  }
	
	@PostMapping(path = "deleteType")
	  public ResponseEntity<?> deleteType( @RequestBody ContentType type) {
	    List<ContentType> resultList = typeRepo.findByTypeId(type.getTypeId());
	    System.out.println("data--->" + resultList);
	    if (!resultList.isEmpty()) {
	    	ContentType cat = resultList.get(0); 
	    	cat.setMarkForDeletion(type.getMarkForDeletion());
	  		cat.setModifiedBy(type.getModifiedBy());
	  		typeRepo.save(cat);
	  		response = new Response (200,"Deleted Successfully",null);

	    } else {
	  		response = new Response (204,"Failed to delete",null);

	    }
    	return new ResponseEntity<>(response,HttpStatus.OK);

	  }

}
