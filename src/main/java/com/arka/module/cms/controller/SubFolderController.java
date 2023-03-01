package com.arka.module.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arka.module.cms.entity.SubFolder;
import com.arka.module.cms.service.SubFolderService;
@RestController
@CrossOrigin
public class SubFolderController {
	
	@Autowired
	SubFolderService sfSer;
	
	@PostMapping("createSubFolder")
	
	public ResponseEntity<?> createSubFolder(@RequestBody SubFolder folder) throws Exception{
		SubFolder save= sfSer.createSubFolder(folder);
		return new ResponseEntity<>(save,HttpStatus.OK);
	}
	
	@PostMapping("viewSubFolders")
	
	public ResponseEntity<?> viewSubFolders(Long folderId,Long userId,String organizationId,long userGroupId,String authName) throws Exception{
		
		List<SubFolder> viewSubFolder = (List<SubFolder>) sfSer.viewSubFolder(folderId, userId,organizationId,userGroupId,authName);
		return new ResponseEntity<>(viewSubFolder,HttpStatus.OK);
	}
	
	@PostMapping(path= "updateSubFolder")
	public ResponseEntity<?> updateSubFolder(Long subFolderId,String name,String description,String email,Long userId) throws Exception{
		 Object updateSubFolder = sfSer.updateSubFolder(subFolderId,name,description,email, userId);
		return new ResponseEntity<>(updateSubFolder,HttpStatus.OK);
	}
	
	@PostMapping("deleteSubFolder")
	public ResponseEntity<?> deleteSubFolder(Long subFolderId,Long userId) throws Exception{
		 Object updateSubFolder = sfSer.deleteSubFolder(subFolderId, userId);
		return new ResponseEntity<>(updateSubFolder,HttpStatus.OK);
	}
	
	
	
	
}
