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
import com.arka.module.cms.repo.ContentCategoryRepo;
import com.arka.module.cms.service.ContentCategoryService;
import com.arka.module.cms.utils.Response;

@RestController
@CrossOrigin
public class ContentCategoryController {
	
	private  Logger log = LogManager.getLogger(ContentCategoryController.class);

	@Autowired
	ContentCategoryService deptCat;
	
	@Autowired
	ContentCategoryRepo catRepo;
	Response response ;

	
	@PostMapping(path = "addCategory")
	
	 public  ResponseEntity<?> addCategory(@RequestBody ContentCategory docCat) {
		String name = docCat.getCategoryName();
		boolean existsByDept = catRepo.existsByCategoryName(name);
		if(existsByDept ) {
			response = new Response (204,"Already Exists");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			ContentCategory save = catRepo.save(docCat);
			return new ResponseEntity<>(save, HttpStatus.OK);
		}
		
	  }
	@PostMapping(path = "getDeptByCategory")
	
	public ResponseEntity<?>  getDeptByCategory(Long deptId){
		
		System.out.println(deptId);
		List<ContentCategory> findByDept = catRepo.findByDeptIdAndMarkForDeletion(deptId,0);
		System.out.println(findByDept);
		log.debug(findByDept);
		return new ResponseEntity<>(findByDept, HttpStatus.OK);
	}
	@PostMapping(path = "updateCategory")
	  public ResponseEntity<?> updateCategory( @RequestBody ContentCategory docCat) {
	    List<ContentCategory> resultList = catRepo.findByCategoryId(docCat.getCategoryId());
	    System.out.println("data--->" + resultList);
	    if (!resultList.isEmpty()) {
	    	ContentCategory cat = resultList.get(0); 
	    	cat.setCategoryName(docCat.getCategoryName());
	    	cat.setModifiedBy(docCat.getModifiedBy());
	      return new ResponseEntity<>(catRepo.save(cat), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>("Data Not Updated",HttpStatus.NOT_FOUND);
	    }
	  }
	@PostMapping(path = "deleteCategory")
	  public ResponseEntity<?> deleteCategory( @RequestBody ContentCategory docCat) {
	    List<ContentCategory> resultList = catRepo.findByCategoryId(docCat.getCategoryId());
	    System.out.println("data--->" + resultList);
	    if (!resultList.isEmpty()) {
	    	ContentCategory cat = resultList.get(0); 
	    	cat.setMarkForDeletion(docCat.getMarkForDeletion());
	  		cat.setModifiedBy(docCat.getModifiedBy());
	  		catRepo.save(cat);
	  		response = new Response (200,"Deleted Successfully",null);

	    	return new ResponseEntity<>(response, HttpStatus.OK);
	    } else {
	  		response = new Response (200,"Failed to delete",null);

	    	return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	@PostMapping(path = "getCategoryActive")
	
	 public  ResponseEntity<?> getCategoryActive( @RequestBody ContentCategory docCat ) {
	   
		  List<ContentCategory> findByEmail = catRepo.findByUserIdAndMarkForDeletionAndOrganizationIdOrderByCreatedOnDesc(docCat.getUserId(), docCat.getMarkForDeletion(),docCat.getOrganizationId());
		 if(!findByEmail.isEmpty()) {
		 return new ResponseEntity<>(findByEmail, HttpStatus.OK);
		  }
		 else {
  	     return new ResponseEntity<>("No Records Found", HttpStatus.OK);

		 }
	  }
}
