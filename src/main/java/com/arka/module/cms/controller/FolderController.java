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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arka.module.cms.dto.FolderDetailsDto;
import com.arka.module.cms.entity.Folder;
import com.arka.module.cms.entity.RolesMaster;
import com.arka.module.cms.entity.UserMaster;
import com.arka.module.cms.exception.ResourceNotFoundException;
import com.arka.module.cms.repo.FolderRepo;
import com.arka.module.cms.repo.UserMasterRepo;
import com.arka.module.cms.service.FolderService;
import com.arka.module.cms.utils.Response;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@SuppressWarnings({"unused"})
@RestController
@CrossOrigin
@Data
@Slf4j
public class FolderController {

	private static final Logger logger = LogManager.getLogger(FolderController.class);

	@Autowired
	FolderRepo fRepo;

	@Autowired
	UserMasterRepo umRepo;

	@Autowired
	FolderService fSer;
	Response response ;
	
	



	@PostMapping(path = "/createFolder")

	public ResponseEntity <?> createFolder(@RequestBody Folder folder ){
		boolean existsByName = fRepo.existsByName(folder.getName());
	
		if(existsByName) {
			response = new Response (204,"Already Exists");
			return new ResponseEntity<>(response,HttpStatus.OK);
		}else {
			Folder save = fRepo.save(folder);
			return new ResponseEntity<>(save,HttpStatus.OK);

		}

	}

	@PostMapping("archeiveFolder")

	public ResponseEntity <?> archeiveFolder(@RequestParam Long folderId,@RequestParam Long userId, @RequestParam String archeiveBy ) throws Exception{
		Folder byId = fRepo.findById(folderId)
				.orElseThrow(() -> new Exception("Folder not found for this id :: " + folderId));

		byId.setArchive(true);
		byId.setArcheivedBy(archeiveBy);
		final Folder arcFol = fRepo.save(byId);
		return ResponseEntity.ok(arcFol);
	}

	@PostMapping("renameFolder")
	public ResponseEntity <?> renameFolder( Long folderId, Long userId,  String modifiedBy ,String folderName) throws ResourceNotFoundException{
		Folder byId = fRepo.findById(folderId)
				.orElseThrow(() -> new ResourceNotFoundException("Folder not found for this id :: " + folderId));
		byId.setName(folderName);
		byId.setModifiedBy(modifiedBy);
		final Folder arcFol = fRepo.save(byId);
		response = new Response (200,"Folder Renamed Successfully",null);

		return new ResponseEntity<>(response,HttpStatus.OK);
	}


	@GetMapping("viewFolders")
	public ResponseEntity <?> viewFolders(@RequestParam long userId,@RequestParam long markForDelete,@RequestParam String organizationId,@RequestParam long  userGroupId,@RequestParam String authName) throws Exception{
		UserMaster user = umRepo.findByUserId(userId);
		System.out.println("user"+ user);
		String roleName = null;
		List<FolderDetailsDto> findActive = null;
		
		if( user.getRole() != null) {
		RolesMaster role = user.getRole();
		 roleName = role.getRoleName();
//		System.out.println("roleName"+ roleName);
		if(roleName.equals("CDSADMIN") || roleName == "CDSADMIN") {
			
			List<Folder> folderList = fRepo.findByMarkForDeletionAndIsArchiveOrderByCreatedOnDesc(markForDelete,false);
			System.out.println("folderList :: "+ folderList);
			findActive = fRepo.findAllActiveFolders();
			System.out.println("folderList Limited data :: "+ findActive);
		}		
		
		else {
//			System.out.println("roleName2"+ roleName);	Goo1, View
//			G001, create
		//	findActive = fRepo.findByUserIdAndMarkForDeletionAndIsArchiveAndOrganizationIdOrderByCreatedOnDesc(userId, markForDelete,false,organizationId);
			findActive= fRepo.retriveFolders(userGroupId, authName);
		}
		}
		
	
			
		if(!findActive.isEmpty()) {
			return new ResponseEntity<>(findActive,HttpStatus.OK);
		}
		
		
		
		else {
			throw new  Exception("Folders not found for this userId");

		
		}
		
		

	}

	@PostMapping("lockFolder")

	public ResponseEntity<?> lockFolder(@RequestParam Long folderId,@RequestParam Long userId, @RequestParam String lockedBy) throws ResourceNotFoundException{
		List<Folder> folder = fRepo.findByFolderId(folderId);
		if(folder.isEmpty()) {
			throw new ResourceNotFoundException("Folder Not Found By Id :: " + folderId);
		}
		Folder update = folder.get(0);
		update.setLocked(true);
		update.setModifiedBy(lockedBy);
		final Folder save = fRepo.save(update);
		return ResponseEntity.ok(save);
	}

	@PostMapping("unArcheiveFolder")

	public ResponseEntity <?> unArcheiveFolder(@RequestParam Long folderId,@RequestParam Long userId, @RequestParam String archeiveBy ) throws ResourceNotFoundException{
		List <Folder> byFolId = fRepo.findByFolderId(folderId);
		if(byFolId.isEmpty()) {
			throw new ResourceNotFoundException("Folder Not Found By Id :: " + folderId);
		}
		Folder byId = byFolId.get(0);
		byId.setArchive(false);
		byId.setArcheivedBy(archeiveBy);
		final Folder arcFol = fRepo.save(byId);
		return ResponseEntity.ok(byId);
	}

	@GetMapping("archeivedFolders")

	public  ResponseEntity <?> archeivedFolders(Long userId){

		List <Folder> archFolder = fRepo.findByUserIdAndIsArchive(userId, true);
		System.out.println("archFolder"+ archFolder);
		Optional.ofNullable(archFolder).orElseThrow(() -> new ResourceNotFoundException("Archive Folders List is empty"));
		return new ResponseEntity<>(archFolder,HttpStatus.OK);
	}
	@PostMapping("deleteFolder")
	public  ResponseEntity <?> deleteFolder(Long userId,Long folderId,String email){
		List <Folder> folList = fRepo.findByFolderId(folderId);
		if(!folList.isEmpty()) {
			Folder folder = folList.get(0);
			folder.setMarkForDeletion(1);
			folder.setModifiedBy(email);
			Folder saveFolder = fRepo.save(folder);
			response = new Response(200,"Folder deleted successfully");
		}else {
			response = new Response (204,"Failed to delete");
		}
		return ResponseEntity.ok().body(response);
	}

	@GetMapping("deletedFolders")
	public  ResponseEntity <?> deletedFolders(Long userId){
		List <Folder> archFolder = fRepo.findByUserIdAndMarkForDeletion(userId, (long) 1);
		if(!archFolder.isEmpty()) {
			response = new Response(200,"Success",archFolder);
		}else {
			response = new Response(204,"Failed",archFolder);
		}
		return ResponseEntity.ok().body(response);
	}
	@PostMapping("restoreFolder")
	public  ResponseEntity <?> restoreFolder(Long userId,Long folderId,String email){
		List <Folder> folList = fRepo.findByFolderId(folderId);
		if(!folList.isEmpty()) {
			Folder folder = folList.get(0);
			folder.setMarkForDeletion(0);
			folder.setModifiedBy(email);
			Folder saveFolder = fRepo.save(folder);
			response = new Response(200,"Folder restored successfully");
		}else {
			response = new Response (204,"Failed to restore");
		}
		return ResponseEntity.ok().body(response);
	}
	@PostMapping("permanentDeleteFolder")
	public  ResponseEntity <?> permanentDeleteFolder(Long userId,Long folderId,String email){
		Object response = fSer.permanentDeleteFolder(userId, folderId, email);
		return ResponseEntity.ok().body(response);
	}

	//   @PostMapping("viewFolders")
	//	
	//	public ResponseEntity <?> viewFolders(@RequestParam List<Integer> userId  ) throws Exception{
	//		List <Folder> findActive = fRepo.findByFolderId(userId);
	//		if(!findActive.isEmpty()) {
	//			return new ResponseEntity<>(findActive,HttpStatus.OK);
	//		}else {
	//			throw new  Exception("Folders not found for this userId :: " + userId);
	//
	//		}
	//		
	//	}
	
	@PostMapping("folder/folderCount")
	public  ResponseEntity <?> folderCount(@RequestBody UserMaster user){
		
		Object response = fSer.countOfFolders(user.getUserId());
		return ResponseEntity.ok().body(response);
		
	}
	

@PostMapping("/auth")
	public  ResponseEntity <?> authorizationPermissionForUserGroupForFolders(@RequestParam long  userGroupId,@RequestParam String authName){

		List<FolderDetailsDto> list = fRepo.retriveFolders(userGroupId, authName);
		
		System.out.println("list----"+list);
	return ResponseEntity.ok().body(list);
	}
	

}
