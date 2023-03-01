package com.arka.module.cms.service;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.arka.module.cms.dto.SubFolderDto;
import com.arka.module.cms.entity.Folder;
import com.arka.module.cms.entity.RolesMaster;
import com.arka.module.cms.entity.SubFolder;
import com.arka.module.cms.entity.UserMaster;
import com.arka.module.cms.repo.SubFolderRepo;
import com.arka.module.cms.repo.UserMasterRepo;
import com.arka.module.cms.utils.Response;

@Service
public class SubFolderService {
	private Logger log = LoggerFactory.getLogger(SubFolderService.class);

	@Autowired
	SubFolderRepo sfRepo;
	Response res;
	
	@Autowired
	UserMasterRepo umRepo;
	
	public SubFolder createSubFolder(@RequestBody SubFolder folder) throws Exception{
		log.debug("Entered save subfolder..."+folder.getSubFolderId());
		SubFolder save = sfRepo.save(folder);
		save.setMarkForDeletion(0);
		final SubFolder sub = sfRepo.save(save);
		return sub;
		
	}
	public Object viewSubFolder(Long folId,Long userId,String orgId,Long userGroupId,String authName) throws Exception {
		List<SubFolder> subList=null;
		List<SubFolderDto> retriveSubFolders=null;
		
		if(userGroupId==0 ||authName==null||authName.isEmpty()) {
			
		JSONObject res=new JSONObject();
		UserMaster user = umRepo.findByUserId(userId);
		String roleName=null;
			

		if( user.getRole() != null) {
		RolesMaster role = user.getRole();	
		 roleName = role.getRoleName();

		if(roleName.equals("CDSADMIN") || roleName == "CDSADMIN" ) {
			subList = sfRepo.findByMarkForDeletionAndFolderIdOrderByCreatedOnDesc(0,folId);
		}
		else {
			subList = sfRepo.findByFolderIdAndUserIdAndMarkForDeletionAndOrganizationIdOrderByCreatedOnDesc(folId, userId, 0,orgId);
			
			//retriveSubFolders = sfRepo.retriveSubFolders(userGroupId, authName);
			}
		}
		}
		
		else {
			
			 retriveSubFolders = sfRepo.retriveSubFolders(userGroupId, authName);
			}
	//	res.put(subList, retriveSubFolders);
		
		if(!retriveSubFolders.isEmpty()) {
			
			return  retriveSubFolders;
		}else {
			
			return subList;
		}
	}
	public Object updateSubFolder (Long subFolderId,String name,String description,String email,Long userId) {
		
	List<SubFolder> findById = sfRepo.findBySubFolderId(subFolderId);
	SubFolder byId = findById.get(0);
	byId.setSubFolderName(name);
	byId.setDescription(description);
	byId.setModifiedBy(email);
	final SubFolder save = sfRepo.save(byId);
	if(save != null) {
		return save;
	}else {
		res = new Response(204, "Failed to update");
		return res;
	}
	
	}
	
	public Object deleteSubFolder(Long id ,Long userId) {
//		List<SubFolder> findById = sfRepo.findBySubFolderIdAndUserId(id,userId);
		List<SubFolder> findById = sfRepo.findBySubFolderId(id);

		if(findById != null ) {
			SubFolder div = findById.get(0);
			div.setMarkForDeletion(1); 
			SubFolder save = sfRepo.save(div);
			res = new Response(200, "Deleted Sucessfully");
		}else {
			res = new Response(204, "Failed to delete");
		}
		return res;
		}
}
                                              