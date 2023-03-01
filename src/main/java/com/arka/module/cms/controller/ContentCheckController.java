package com.arka.module.cms.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arka.module.cms.entity.ContentCheck;
import com.arka.module.cms.entity.ContentHistory;
import com.arka.module.cms.entity.ContentMaster;
import com.arka.module.cms.exception.ResourceNotFoundException;
import com.arka.module.cms.repo.ContentCheckRepo;
import com.arka.module.cms.repo.ContentHistoryRepo;
import com.arka.module.cms.repo.ContentMasterRepo;
import com.arka.module.cms.service.ContentCheckService;

/**
 * @author acer
 *
 */
@SuppressWarnings({"unused"})
@RestController
@CrossOrigin
public class ContentCheckController {
	@SuppressWarnings("unused")
	private  Logger log = LogManager.getLogger(ContentCheckController.class);

	@Autowired
	ContentCheckRepo ccRepo;
	
	@Autowired
	ContentCheckService ccSer;
	
	@Autowired
	ContentMasterRepo  cmRepo;
	
	@Autowired
	ContentHistoryRepo chRepo;
	/**
	 * @param checkIn
	 * @return
	 * @throws ResourceNotFoundException
	 */
	  
	@PostMapping("/checkInDocument")
	public  ResponseEntity<?> checkInDocument(@RequestBody ContentMaster checkIn) throws ResourceNotFoundException{

		List <ContentMaster> list = cmRepo.findByDocId(checkIn.getDocId());		
		ContentMaster cat = list.get(0);
		float version = cat.getVersion();
		cat.setContent(checkIn.getContent());
		cat.setVersion(version+1);
		cat.setAccess("Private");
		cat.setAuthorization("Confidential");		
		final ContentMaster  save = cmRepo.save(cat);
		System.out.println("save" + save);
		if(!ObjectUtils.isEmpty(save)) {
		ContentHistory ch = new ContentHistory();
		ch.setAccess("Private");
		ch.setAuthorize("Confidential");
		ch.setFolder(checkIn.getFolder());
		ch.setDocId(checkIn.getDocId());
		ch.setCategory(checkIn.getCatId());
		ch.setDept(checkIn.getDeptId());
		ch.setExpiryDate(checkIn.getExpiryDate());
		ch.setNewName(checkIn.getName());
		ch.setPrefix(checkIn.getPrefix());
		ch.setRecordVersion(version+1);
		ch.setLocation(checkIn.getLocation());
		ch.setModifiedContent(checkIn.getContent());
		ch.setDept(checkIn.getDeptId());
		ch.setUser(checkIn.getUser());
		ch.setRetentionDate(checkIn.getRetentionDate());
		ch.setUuid(cat.getRandomUuid());
		ch.setStatus(checkIn.getStatus());
		ch.setSize(checkIn.getSize());
		final ContentHistory history = chRepo.save(ch);
		System.out.println("history-->"+ history);
		if(!ObjectUtils.isEmpty(history)) {
			ContentCheck check = new ContentCheck();
			check.setDocId(checkIn.getDocId());
			check.setCheckStatus("CheckedIn");
			check.setCheckedBy(checkIn.getCreatedBy());
			check.setCreatedBy(checkIn.getCreatedBy());
			check.setCheckedDate(new Timestamp(new Date().getTime()));
			check.setUser(checkIn.getUser());
			final ContentCheck checkin= ccRepo.save(check);
		}
	}
        return ResponseEntity.ok().body("Document Checked In Successfully");
        

	}
	
	
	
	@PostMapping(path = "/checkOutDocument")
	public ResponseEntity<?> checkOutDocument(@RequestBody ContentCheck checkOut) throws Exception{
		ContentCheck save = ccRepo.save(checkOut);
		if(ObjectUtils.isEmpty(save)) {
			throw new Exception(); 
	    }
		
		return ResponseEntity.ok().body(save);
	}
	
	

}
