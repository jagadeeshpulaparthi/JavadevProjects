package com.arka.module.cms.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;

import com.arka.module.cms.entity.ContentCheck;
import com.arka.module.cms.entity.ContentHistory;
import com.arka.module.cms.entity.ContentMaster;
import com.arka.module.cms.entity.NumberRange;
import com.arka.module.cms.repo.ContentAttributeRepo;
import com.arka.module.cms.repo.ContentCheckRepo;
import com.arka.module.cms.repo.ContentHistoryRepo;
import com.arka.module.cms.repo.ContentMasterRepo;
import com.arka.module.cms.repo.NumberRangeRepo;
import com.arka.module.cms.utils.GenerateId;

@Service
public class ContentMasterService {
	
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
	NumberRangeRepo nRepo;
	
	@Value("${app.documentUrl}")
	private String documentUrl;
	
	public Object uploadDocument(@RequestBody ContentMaster addDocument) throws Exception {
		ContentMaster upDoc = cmRepo.save(addDocument);
		Long docId = upDoc.getDocId();
		String documentId = upDoc.getDocumentId();
		String documentLink = documentUrl+upDoc.getDocId();
		String prefix = addDocument.getPrefix();
		String id ="";
		upDoc.setDocumentLink(documentLink);
		if(addDocument.getDocumentId().isEmpty()|| addDocument.getDocumentId() == null) {
			long count = nRepo.countByType(prefix);
			System.out.println("count-->"+count);
			if(count ==0) {
				NumberRange nr = new NumberRange();
				nr.setId((long)1);
				nr.setType(prefix);
				nRepo.save(nr);
				id=GenerateId.padLeftZeros(prefix,String.valueOf(1), 5);
				System.out.println("id---->" + id);
			}else {
				id=GenerateId.padLeftZeros(prefix,String.valueOf(count), 5);
			}
			System.out.println("id: "+id);
			List <ContentMaster> lst = cmRepo.findByDocumentId(id);
			System.out.println("idss: "+lst);
			if(lst==null) {
				
			}else {
				//String docID = lst.getDocumentId();
				NumberRange nr= new NumberRange();
				id=GenerateId.padLeftZeros(prefix,String.valueOf(count+1), 5);
				nr.setId(count+1);
				nr.setType(prefix);				
				nRepo.save(nr);				
			}
			upDoc.setDocumentId(id);
		}
//		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	        Date date = new Date();
//	        System.out.println("Current Date " + dateFormat.format(date));
//
//	        // Convert Date to Calendar
//	        Calendar c = Calendar.getInstance();
//	        c.setTime(date);
//	        // Perform addition/subtraction
//	        c.add(Calendar.YEAR, 2);  
//	        // Convert calendar back to Date
//	        Date currentDatePlusOne = c.getTime();
//	        System.out.println("currentDatePlusOne--->"+ currentDatePlusOne);
//		long docId = upDoc.getDocId();
//		if(!ObjectUtils.isEmpty(upDoc)) {
		ContentHistory hDoc = new ContentHistory();
		// BeanUtils.copyProperties(upDoc, hDoc);
		hDoc.setDept(upDoc.getDeptId());
		hDoc.setCategory(upDoc.getCatId());
		hDoc.setFolder(upDoc.getFolder());
		hDoc.setLocation(upDoc.getLocation());
		hDoc.setOrganizationId(upDoc.getOrganizationId());
		hDoc.setPrefix(upDoc.getPrefix());
		hDoc.setExpiryDate(upDoc.getExpiryDate());
		hDoc.setRetentionDate(upDoc.getRetentionDate());
		hDoc.setSize(upDoc.getSize());
		hDoc.setRecordVersion(upDoc.getVersion());
		hDoc.setType(upDoc.getTypId());
		hDoc.setAccess(upDoc.getAccess());
		hDoc.setNestedFolderId(upDoc.getNestedFolderId());
		hDoc.setSubFolderId(upDoc.getSubFolderId());
		hDoc.setAuthorize(upDoc.getAuthorization());
		hDoc.setStatus(upDoc.getStatus());
		hDoc.setCreatedBy(upDoc.getCreatedBy());
		hDoc.setUser(upDoc.getUser());
		hDoc.setModifiedContent(upDoc.getContent());
		hDoc.setNewName(upDoc.getName());
		hDoc.setDocId(upDoc.getDocId());
		hDoc.setUuid(upDoc.getRandomUuid());
		hDoc.setFileType(upDoc.getFileType());
		 //System.out.println("ContentHistory--->"+ hDoc);
		ContentHistory resList = chRepo.save(hDoc);
		if(!ObjectUtils.isEmpty(resList)) {
			ContentCheck conCheck = new ContentCheck();
			conCheck.setCheckedBy(resList.getCreatedBy());
			conCheck.setCreatedBy(resList.getCreatedBy());
			conCheck.setDocId(resList.getDocId());
			conCheck.setCheckStatus("CheckedIn");
			conCheck.setUser(resList.getUser());
			conCheck.setOrganizationId(resList.getOrganizationId());
			ccRepo.save(conCheck);
			System.out.println("--->"+ conCheck);
		}else {
			throw new Exception();
		}
		Map mp = new HashMap();
		mp.put("status", 200);
		mp.put("message","Uploaded  Successfully");
		mp.put("docId",docId);
		mp.put("documentId", id);
		
		return mp;
	}
	
	

}
