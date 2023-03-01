package com.arka.module.cms.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.arka.module.cms.entity.CMSSearchMaster;
import com.arka.module.cms.model.DTO.CommonVO;
import com.arka.module.cms.repo.CMSSearchMasterRepo;
import com.arka.module.cms.utils.ContentSearchCriteria;
/**
 * @author Arkatiss123
 *
 */
@RestController
@CrossOrigin
public class CMSSearchMasterController {
	@Autowired
	CMSSearchMasterRepo repo;

	@Autowired
	ContentSearchCriteria contentCriteria;

	/**
	 * @author Surekha
	 * 
	 * @param  No
	 * This api is to get all data for CMS Search master
	 * @return CMSSearchMaster.class
	 */

	@GetMapping("getSearchdata")
	public  ResponseEntity<?> getSearchdata(){
		List<CMSSearchMaster> response=repo.findAll();
		return new ResponseEntity<>( response,HttpStatus.OK);
	}

	/**
	 * @author Arkatiss123
	 *
	 */
	@PostMapping("getSearchCriteriaData")
	public ResponseEntity <?>  getSearchCriteriaData(@RequestBody CommonVO content){
		List<?> contentSearch = contentCriteria.getContentSearch(content.getType(),content.getUserId(),content.getFromDate(),content.getToDate(),content.getSearchString());
		return new ResponseEntity<>( contentSearch,HttpStatus.OK);
	}

}
