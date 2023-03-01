package com.arka.module.cms.controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.arka.module.cms.entity.ContentAttribute;
import com.arka.module.cms.entity.ContentCheck;
import com.arka.module.cms.entity.ContentHistory;
import com.arka.module.cms.entity.ContentMaster;
import com.arka.module.cms.entity.ContentType;
import com.arka.module.cms.entity.Folder;
import com.arka.module.cms.entity.RolesMaster;
import com.arka.module.cms.entity.UserMaster;
import com.arka.module.cms.exception.ResourceNotFoundException;
import com.arka.module.cms.model.DTO.CommonVO;
import com.arka.module.cms.repo.ContentAttributeRepo;
import com.arka.module.cms.repo.ContentCheckRepo;
import com.arka.module.cms.repo.ContentHistoryRepo;
import com.arka.module.cms.repo.ContentMasterDto;
import com.arka.module.cms.repo.ContentMasterRepo;
import com.arka.module.cms.repo.UserMasterRepo;
import com.arka.module.cms.service.ContentMasterService;
import com.arka.module.cms.utils.Response;

/**
 * @author acer
 *
 */
/**
 * @author acer
 *
 */
@SuppressWarnings({ "unused","rawtypes","unchecked"})
@RestController
@CrossOrigin
public class ContentMasterController {
	Map map=new HashMap();

	private static final Logger logger = LogManager.getLogger(ContentMasterController.class);

	@Autowired
	ContentMasterRepo cmRepo;
	//
	@Autowired
	ContentHistoryRepo chRepo;

	@Autowired
	ContentCheckRepo ccRepo;

	@Autowired
	ContentAttributeRepo caRepo;

	@Autowired
	ContentMasterService cmSer;

	@Autowired
	UserMasterRepo umRepo;

	Response response ;

	@PostMapping("/uploadDocument")

	public ResponseEntity<?> uploadDocument(@RequestBody ContentMaster addDocument) throws Exception {
		Object uploadDocument = cmSer.uploadDocument(addDocument);
//		map.put("status", 200);
//		map.put("message", uploadDocument);
		return new ResponseEntity<>(uploadDocument, HttpStatus.OK);

	}

	@PostMapping("archiveDocument")

	public ResponseEntity <?> archiveDocument(@RequestParam String archiveBy,@RequestParam Long docId,@RequestParam Long userId) throws ResourceNotFoundException {
		ContentMaster con =  cmRepo.findById(docId).orElseThrow(() -> new ResourceNotFoundException("Document not found for this id :: " + docId));
		con.setStatus("Archive");	
		con.setModifiedBy(archiveBy);
		ContentMaster arcDoc= cmRepo.save(con);
		ContentAttribute caDoc = new ContentAttribute();

		if(!ObjectUtils.isEmpty(arcDoc)) {
			caDoc.setAttrStatus("Archive");
			caDoc.setAttrBy(archiveBy);
			caDoc.setDocId(docId);
			caDoc.setUserId(userId);
		}
		ContentAttribute save = caRepo.save(caDoc);
		return  ResponseEntity.ok(save);
	}
	@PostMapping("unArcheiveDocument")

	public ResponseEntity <?> unArcheiveDocument(String email, Long docId, Long userId) throws ResourceNotFoundException {
		ContentMaster con =  cmRepo.findById(docId).orElseThrow(() -> new ResourceNotFoundException("Document not found for this id :: " + docId));
		con.setStatus("New");	
		con.setModifiedBy(email);
		ContentMaster arcDoc= cmRepo.save(con);
		ContentAttribute caDoc = new ContentAttribute();

		if(!ObjectUtils.isEmpty(arcDoc)) {
			caDoc.setAttrStatus("UnArchive");
			caDoc.setAttrBy(email);
			caDoc.setDocId(docId);
			caDoc.setUserId(userId);
		}
		ContentAttribute save = caRepo.save(caDoc);
		return  ResponseEntity.ok(save);
	}

	@GetMapping("versionsOfDocument")

	public ResponseEntity<?> versionsOfDocument(Long docId,Long userId){
		List<ContentHistory> verList = chRepo.findByDocId(docId);
		return ResponseEntity.ok(verList);
	}
	@PostMapping("versionRollback")

	public ResponseEntity<?> contentUpdate(float version,Long userId,Long docId) throws ResourceNotFoundException{
		List<ContentHistory> chList = chRepo.findByRecordVersionAndDocId(version,docId);
		ContentHistory ch = chList.get(0);
		ContentMaster cmList = cmRepo.findById(docId).orElseThrow(() -> new ResourceNotFoundException("Document Not Found ::" + docId));
		cmList.setContent(ch.getModifiedContent());
		cmList.setVersion(ch.getRecordVersion());
		final ContentMaster update = cmRepo.save(cmList);
		return ResponseEntity.ok(update);

	}

	@GetMapping("archivedDocuments")

	public ResponseEntity<?>  archivedDocuments(Long userId,Long folderId) throws Exception{
		UserMaster user = umRepo.findByUserId(userId);
		String roleName=null;
		List<ContentMaster> archList=null;
		if( user.getRole() != null) {
			RolesMaster role = user.getRole();	
			 roleName = role.getRoleName();

		if(roleName.equals("CDSADMIN") || roleName == "CDSADMIN" ) {
			archList = cmRepo.findByStatusAndFolderIdOrderByCreatedOnDesc("Archive",folderId);
		}
		}else {
			archList = cmRepo.findByFolderIdAndUserIdAndStatus(userId, folderId, "Archive");
		}
		if(!archList.isEmpty()) {
		return new ResponseEntity<>(archList,HttpStatus.OK);
		}
		else {
			throw new  Exception("No archived documents in folder with id::"+ folderId);
			

		}
	}
	@GetMapping("viewDocumentsInUser")
	public ResponseEntity<?>  viewDocumentsInUser(Long userId){
		List <ContentMaster>  archList = cmRepo.findByUserId(userId);
		Optional.ofNullable(archList).orElseThrow(() -> new ResourceNotFoundException("Documents not found in the user" + userId) );
		return new ResponseEntity<>(archList,HttpStatus.OK);
	}
	@GetMapping("getDocumentByDocID")

	public ResponseEntity<?> getDocumentByDocID(Long docId){
		List<ContentMaster> docListById = cmRepo.findByDocId(docId);
		return ResponseEntity.ok(docListById);
	}
	@GetMapping("viewDocumentsInFolder")
	public ResponseEntity<?>  viewDocumentsInFolder(Long userId,Long folderId,String organizationId) throws Exception{
		UserMaster user = umRepo.findByUserId(userId);
		String roleName=null;
		List<ContentMasterDto > archList=null;
		if( user.getRole() != null) {
			RolesMaster role = user.getRole();	
			 roleName = role.getRoleName();
			
		if(roleName.equals("CDSADMIN") || roleName == "CDSADMIN" ) {
			archList = cmRepo.findByFolderIdAndStatusOrderByCreatedOnDesc(folderId,"New");
		}
		}else {
			archList = cmRepo.findByUserIdAndFolderIdAndStatusAndOrganizationIdOrderByCreatedOnDesc(userId, folderId, "New",organizationId);
		}
		 if(!archList.isEmpty()) {

		//System.out.println("archList" + archList);
		//Optional.ofNullable(archList).orElseThrow(() -> new ResourceNotFoundException("Documents not found in the user" + userId) );
		return new ResponseEntity<>(archList,HttpStatus.OK);
		 }
		else {
			throw new  Exception("Documents not found in the user::"+ userId);
			

		}

	}
	@PostMapping("viewDocumentsInSubFolder")
	public ResponseEntity<?>  viewDocumentsInSubFolder(Long userId,Long folderId,Long subFolderId,String organizationId) throws Exception{
		UserMaster user = umRepo.findByUserId(userId);
		String roleName=null;
		
		List<ContentMasterDto > archList=null;
		if( user.getRole() != null) {
			RolesMaster role = user.getRole();	
			 roleName = role.getRoleName();
		if(roleName.equals("CDSADMIN") || roleName == "CDSADMIN" ) {
			archList = cmRepo.findByFolderIdAndSubFolderIdAndStatusOrderByCreatedOnDesc(folderId,subFolderId,"New");
		}
		}else {
			archList = cmRepo.findByUserIdAndFolderIdAndSubFolderIdAndStatusAndOrganizationIdOrderByCreatedOnDesc(userId, folderId,subFolderId, "New",organizationId);
		}
		 if(!archList.isEmpty()) {
	
		return new ResponseEntity<>(archList,HttpStatus.OK);
	}
		else {
			throw new  Exception("Documents not found in the user::"+ userId);
			

		}
	}


@PostMapping("viewDocumentsInNestedFolder")
	
	public ResponseEntity<?>  viewDocumentsInNestedFolder(Long userId,Long folderId,Long subFolderId,Long nestedFolderId,String organizationId) throws Exception{
		UserMaster user = umRepo.findByUserId(userId);
//		
		String roleName=null;
		List<ContentMasterDto > archList=null;
		if( user.getRole() != null) {
			RolesMaster role = user.getRole();	
			 roleName = role.getRoleName();
		
		if(roleName.equals("CDSADMIN") || roleName == "CDSADMIN" ) {
			archList = cmRepo.findByFolderIdAndSubFolderIdAndNestedFolderIdAndStatusOrderByCreatedOnDesc(folderId,subFolderId,nestedFolderId,"New");
		}
		}else {
			archList = cmRepo.findByUserIdAndFolderIdAndSubFolderIdAndNestedFolderIdAndStatusAndOrganizationIdOrderByCreatedOnDesc(userId, folderId,subFolderId,nestedFolderId, "New",organizationId);

		}
		 if(!archList.isEmpty()) {
//		
		return new ResponseEntity<>(archList,HttpStatus.OK);
		 }
		else {
			throw new  Exception("Documents not found in the user::"+ userId);
			

		}
	}

	@GetMapping("viewDocument")
	public ResponseEntity<?>  viewDocument(Long userId,Long docId) throws ResourceNotFoundException{
		
		ContentMaster archList = cmRepo.findById(docId).orElseThrow(() -> new ResourceNotFoundException("Document not found with id ::" + docId));
		ContentType typId = archList.getTypId();
		map.put("document", archList.getContent());
		map.put("filetype",archList.getFileType() );

		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
//	@GetMapping("viewDocuments")
//	public ResponseEntity<?>  viewDocuments(String docId) {
//		ContentMaster archList = cmRepo.findByDocumentId(docId);
//		ContentType typId = archList.getTypId();
//		map.put("document", archList.getContent());
//		map.put("filetype",archList.getFileType() );
//		map.put("description",archList.getComments());
//		if(archList == null) {
//			response = new Response(204,"No Document Found");
//		}else {
//			response = new Response(200,"Success",map);
//
//		}		
//
//		return new ResponseEntity<>(response,HttpStatus.OK);
//	}
	
	
	@PostMapping("updateDocument")
	public  ResponseEntity<?> updateDocument(@RequestBody ContentMaster checkIn) throws ResourceNotFoundException{

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
			ch.setFolder(cat.getFolder());
			ch.setDocId(cat.getDocId());
			ch.setCategory(cat.getCatId());
			ch.setDept(cat.getDeptId());
			ch.setExpiryDate(cat.getExpiryDate());
			ch.setNewName(cat.getName());
			ch.setPrefix(cat.getPrefix());
			ch.setRecordVersion(version+1);
			ch.setLocation(cat.getLocation());
			ch.setModifiedContent(checkIn.getContent());
			ch.setDept(cat.getDeptId());
			ch.setUser(cat.getUser());
			ch.setRetentionDate(cat.getRetentionDate());
			ch.setUuid(cat.getRandomUuid());
			ch.setStatus(cat.getStatus());
			ch.setSize(cat.getSize());
			final ContentHistory history = chRepo.save(ch);
			System.out.println("history-->"+ history);
			if(!ObjectUtils.isEmpty(history)) {
				ContentCheck check = new ContentCheck();
				check.setDocId(checkIn.getDocId());
				check.setCheckedBy(checkIn.getCreatedBy());
				check.setCreatedBy(checkIn.getCreatedBy());
				check.setCheckedDate(new Timestamp(new Date().getTime()));
				check.setUser(checkIn.getUser());
				final ContentCheck checkin= ccRepo.save(check);
			}
			map.put("message", "Document updated Successfully");
			map.put("status", "200");
		}
		return ResponseEntity.ok().body(map);


	}
	@PostMapping("moveToTrash")
	public ResponseEntity<?> moveToTrash(String email,Long docId ,Long userId){
		List <ContentMaster> list = cmRepo.findByDocId(docId);	
		ContentMaster cat = list.get(0);
		cat.setStatus("Trash");
		cat.setModifiedBy(email);
		final ContentMaster cm = cmRepo.save(cat);
		response = new Response (200,"Document moved to trash",null);
		return  ResponseEntity.ok().body(response);

	}
	@GetMapping("trashDocuments")
	public ResponseEntity<?> trashDocuments(Long userId,Long folderId) throws Exception{
		UserMaster user = umRepo.findByUserId(userId);
		RolesMaster role = user.getRole();
		String roleName = role.getRoleName();
		List<ContentMaster> archList;

		if(roleName.equals("CDSADMIN") || roleName == "CDSADMIN" ) {
			archList = cmRepo.findByStatusAndFolderIdOrderByCreatedOnDesc("Trash",folderId);
		}else {
			archList = cmRepo.findByFolderIdAndUserIdAndStatus(userId, folderId,"Trash");
		}
		if(archList.isEmpty()) {
			response =  new Response (204,"List is empty");
			return new ResponseEntity<>(response,HttpStatus.OK);

		}else {
			return new ResponseEntity<>(archList,HttpStatus.OK);

		}

	}

	@PostMapping("restoreFromTrash")
	public ResponseEntity<?> restoreFromTrash(Long userId,Long docId,Long folderId) throws Exception{
		ContentMaster trashDoc = cmRepo.findByUserIdAndFolderIdAndDocIdAndStatus(userId,folderId, docId,"Trash");
		if(trashDoc == null) {
			throw new Exception("Failed to restore");
		}else {
			trashDoc.setStatus("New");
			ContentMaster master = cmRepo.save(trashDoc);
			response = new Response (200,"Document restored successfully",master);
			return  ResponseEntity.ok().body(response);
		}

	}
	@PostMapping("deleteDocumentInAdmin")
	public ResponseEntity<?> deleteDocumentInAdmin(Long userId,Long docId) throws Exception{
		ContentMaster trashDoc = cmRepo.findByUserIdAndDocId(userId, docId);
		if(trashDoc == null) {
			throw new Exception("Failed to delete");
		}else {
			trashDoc.setMarkForDeletion(1);
			trashDoc.setStatus("Deleted");
			ContentMaster master = cmRepo.save(trashDoc);
			response = new Response (200,"Document deleted successfully");
			return  ResponseEntity.ok().body(response);
		}
	}
	@GetMapping("getDocById")
	public ResponseEntity<?> getDocById(Long docId )throws Exception{
		List <ContentMaster> doc = cmRepo.findByDocId(docId);
		ContentMaster cm = doc.get(0);
		String document = cm.getContent();
		if(doc== null) {
			throw new Exception("Failed to retrieve");
		}else {
			//			response = new Response (200,"Document retrived successfully",document);
			return  ResponseEntity.ok().body(document);
		}
	}
	


}

