package com.arka.module.cms.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.arka.module.cms.entity.NestedFolder;
import com.arka.module.cms.entity.RolesMaster;
import com.arka.module.cms.entity.SubFolder;
import com.arka.module.cms.entity.UserMaster;
import com.arka.module.cms.repo.NestedFolderRepo;
import com.arka.module.cms.repo.UserMasterRepo;
import com.arka.module.cms.utils.Response;

@Service
public class NestedFolderService {
	private Logger log = LoggerFactory.getLogger(NestedFolderService.class);
	
	@Autowired
	NestedFolderRepo nfRepo;
	
	Response res;
	
	@Autowired
	UserMasterRepo umRepo;
	
	public NestedFolder createNestedFolder(@RequestBody NestedFolder folder) throws Exception{
		log.debug("Entered save subfolder..."+folder.getNestedFolderId());
		NestedFolder save = nfRepo.save(folder);
		save.setMarkForDeletion(0);
		final NestedFolder sub = nfRepo.save(save);
		return sub;
		
	}
	public List<NestedFolder> viewNestedFolder(Long folId,Long userId,Long subId,String orgId) throws Exception {
		UserMaster user = umRepo.findByUserId(userId);
		//RolesMaster role = user.getRole();
		String roleName=null;
//		String roleName = role.getRoleName();
		List<NestedFolder> subList=null;
		RolesMaster role = user.getRole();	
		 roleName = role.getRoleName();
		System.out.println("roleName"+ roleName);
		if(roleName.equals("CDSADMIN") || roleName == "CDSADMIN" ) {
			subList = nfRepo.findByMarkForDeletionAndFolderIdAndSubFolderIdOrderByCreatedOnDesc(0,folId,subId);
		}else {
			subList = nfRepo.findByFolderIdAndSubFolderIdAndUserIdAndMarkForDeletionAndOrganizationIdOrderByCreatedOnDesc(folId, subId,userId, 0,orgId);		}
		if(subList.isEmpty()) {
			throw new Exception ("No NestedFolders in subfolder -----"+subId);
			
		}else {
			return subList;
		}
	}
	public Object updateSubFolder (Long nesFolderId,String name,String description,String email,Long userId) {
		
	List<NestedFolder> findById = nfRepo.findByNestedFolderId(nesFolderId);
	NestedFolder byId = findById.get(0);
	byId.setNestFolderName(name);
	byId.setDescription(description);
	byId.setModifiedBy(email);
	final NestedFolder save = nfRepo.save(byId);
	if(save != null) {
		return save;
	}else {
		res = new Response(204, "Failed to update");
		return res;
	}
	
	}
	
	public Object deleteSubFolder(Long id ,Long userId) {
		List<NestedFolder> findById = nfRepo.findByNestedFolderId(id);
		if(findById != null ) {
			NestedFolder div = findById.get(0);
			div.setMarkForDeletion(1); 
			NestedFolder fol = nfRepo.save(div);
			res = new Response(200, "Deleted Sucessfully",fol);
		}else {
			res = new Response(204, "Failed to delete");
		}
		return res;
		}

}
