package com.arka.module.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arka.module.cms.service.ContentService;

@RestController
@CrossOrigin

public class Content {
	
	@Autowired
	ContentService conSer;
	
	@GetMapping("getContentFromNestedFolder")
	public ResponseEntity<?> getContentFromNestedFolder(String company ,String division,String name) throws Exception {
		Object content = conSer.getContentFromNested(company, division, name);
		return new ResponseEntity<>(content,HttpStatus.OK);

	}
	
	@GetMapping("getContentFromFolder")
	public ResponseEntity<?> getContentFromFolder(String company ,String division,String folder) throws Exception {
		Object content = conSer.getContentFromFolder(company, division, folder);
		return new ResponseEntity<>(content,HttpStatus.OK);

	}
	
	@GetMapping("getContentFromSubFolder")
	public ResponseEntity<?> getContentFromSubFolder(String company ,String division,String subFolder) throws Exception {
		Object content = conSer.getContentFromFolder(company, division, subFolder);
		return new ResponseEntity<>(content,HttpStatus.OK);
	}
	
//	@GetMapping("getContent")
//	public ResponseEntity<?> getContent(String company ,String division,String folder,String subFolder,String nestFolder) throws Exception {
//		Object content = conSer.getContentFromFolderSubFolderNestedFolder(company, division, folder,subFolder,nestFolder);
//		return new ResponseEntity<>(content,HttpStatus.OK);
//	}
	
	@GetMapping("getContent")
	public ResponseEntity<?> getContentForEcomm( String company ,String storeId,String content) throws Exception {
		Object data = conSer.getContentForEcomm(company, storeId, content);
		return new ResponseEntity<>(data,HttpStatus.OK);
	}
	

}
