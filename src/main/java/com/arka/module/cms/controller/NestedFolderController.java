package com.arka.module.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.arka.module.cms.entity.NestedFolder;
import com.arka.module.cms.service.NestedFolderService;

@RestController
@CrossOrigin
public class NestedFolderController {
	
	@Autowired
	NestedFolderService nfSer;
	
    @PostMapping("createNestedFolder")
	
	public ResponseEntity<?> createNestedFolder(@RequestBody NestedFolder folder) throws Exception{
	    NestedFolder save= nfSer.createNestedFolder(folder);
		return new ResponseEntity<>(save,HttpStatus.OK);
	}
	
	@PostMapping("viewNestedFolders")
	
	public ResponseEntity<?> viewNestedFolders(Long folderId,Long userId,Long subFolderId,String organizationId) throws Exception{
		List<NestedFolder> viewSubFolder = nfSer.viewNestedFolder(folderId, userId,subFolderId,organizationId);
		return new ResponseEntity<>(viewSubFolder,HttpStatus.OK);
	}
	
	@PostMapping("updateNestedFolder")
	public ResponseEntity<?> updateNestedFolder(Long nestedFolderId,String name,String description,String email,Long userId) throws Exception{
		 Object updateSubFolder = nfSer.updateSubFolder(nestedFolderId,name,description,email, userId);
		return new ResponseEntity<>(updateSubFolder,HttpStatus.OK);
	}
	
	@PostMapping("deleteNestedFolder")
	public ResponseEntity<?> deleteNestedFolder(Long nestedFolderId,Long userId) throws Exception{
		 Object updateSubFolder = nfSer.deleteSubFolder(nestedFolderId, userId);
		return new ResponseEntity<>(updateSubFolder,HttpStatus.OK);
	}
	

}
