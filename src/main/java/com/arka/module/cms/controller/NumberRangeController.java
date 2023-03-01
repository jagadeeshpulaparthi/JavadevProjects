package com.arka.module.cms.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arka.module.cms.entity.ContentMaster;
import com.arka.module.cms.entity.NumberRange;
import com.arka.module.cms.repo.ContentMasterRepo;
import com.arka.module.cms.repo.NumberRangeRepo;
import com.arka.module.cms.service.NumberRangeService;
import com.arka.module.cms.utils.GenerateId;

@SuppressWarnings({"unused","rawtypes"})
@RestController
@CrossOrigin
public class NumberRangeController {
	private static final Logger logger = LogManager.getLogger(NumberRangeController.class);

	@Autowired
	NumberRangeRepo nRepo;

	@Autowired
	ContentMasterRepo cmRepo;

	@Autowired
	NumberRangeService nSer;

	@SuppressWarnings("unchecked")
	@PostMapping(path ="/getId",produces = "application/json")


	public  ResponseEntity<?> getId(@RequestParam String Prefix) {
		String id = "";
		Map map=new HashMap();

		long count = nRepo.countByType(Prefix);
		System.out.println("count-->"+count);
		if(count ==0) {
			NumberRange nr = new NumberRange();
			nr.setId((long)1);
			nr.setType(Prefix);
			nRepo.save(nr);
			id=GenerateId.padLeftZeros(Prefix,String.valueOf(1), 5);
			System.out.println("id---->" + id);
		}else {
			id=GenerateId.padLeftZeros(Prefix,String.valueOf(count), 5);
		}
		System.out.println("id: "+id);
		List <ContentMaster >lst = cmRepo.findByDocumentId(id);
		System.out.println("idss: "+lst);
		if(lst==null) {
			map.put("doc_id", id);
			map.put("status", 200);
		}else {
			//String docID = lst.getDocumentId();
			NumberRange nr= new NumberRange();
			id=GenerateId.padLeftZeros(Prefix,String.valueOf(count+1), 5);
			nr.setId(count+1);
			nr.setType(Prefix);				
			nRepo.save(nr);
			map.put("doc_id", id);
			map.put("status", 200);
		}
		return  ResponseEntity.ok(map);

	}




}
