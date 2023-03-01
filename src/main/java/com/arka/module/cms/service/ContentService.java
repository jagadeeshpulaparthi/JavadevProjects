package com.arka.module.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arka.module.cms.entity.Folder;
import com.arka.module.cms.entity.NestedFolder;
import com.arka.module.cms.entity.SubFolder;
import com.arka.module.cms.repo.ContentDto;
import com.arka.module.cms.repo.ContentMasterDto;
import com.arka.module.cms.repo.ContentMasterRepo;
import com.arka.module.cms.repo.FolderRepo;
import com.arka.module.cms.repo.NestedFolderRepo;
import com.arka.module.cms.repo.SubFolderRepo;
import com.arka.module.cms.utils.Response;

@Service
public class ContentService {
	
	@Autowired
	NestedFolderRepo nfRepo;
	
	@Autowired
	ContentMasterRepo cmRepo;
	
	@Autowired
	FolderRepo fRepo;
	
	@Autowired
	SubFolderRepo sfRepo;
	
	Response res;
	
	public Object getContentFromNested(String company ,String division,String folName) throws Exception {
		String orgId = company+"_"+division;
		NestedFolder  folList = nfRepo.findByNestFolderNameAndOrganizationIdAllIgnoreCase(folName,orgId) ;
		Long id = folList.getNestedFolderId();
		List<ContentDto> imageList = cmRepo.findByNestedFolderIdAndStatusAndOrganizationIdAllIgnoreCase(id,"New", orgId);
//		if(imageList.isEmpty() || imageList == null) {
//			List<ContentDto> imageList = cmRepo.findByNestedFolderIdAndStatusAndOrganizationIdAllIgnoreCase(id,"New", orgId);
//
//		}
		
		Optional.ofNullable(imageList).orElseThrow(() -> new Exception("No images found in ::"+ folName));
		res = new Response(200,"Success",imageList);
		return res;
	}
	
	public Object getContentFromFolder(String company ,String division ,String folder) throws Exception {
		String orgId = company+"_"+division;
		Folder folList = fRepo.findByNameAndOrganizationIdAllIgnoreCase(folder, orgId);
		Long folderId = folList.getFolderId();
		List<ContentMasterDto> list = cmRepo.findByFolderIdAndMarkForDeletionAndOrganizationIdAllIgnoreCase(folderId,0, orgId);
		Optional.ofNullable(list).orElseThrow(() -> new Exception("No images found in ::"+ folder));
		res = new Response(200,"Success",list);
		return res;
	}
	
	public Object getContentFromSubFolder(String company ,String division,String subFolder) throws Exception{
		String orgId = company+"_"+division;
		SubFolder subList = sfRepo.findBySubFolderNameAndOrganizationIdAllIgnoreCase(subFolder,orgId);
		Long subId = subList.getSubFolderId();
		List<ContentMasterDto> findBySubFolderIdAndOrganizationIdAllIgnoreCase = cmRepo.findBySubFolderIdAndOrganizationIdAllIgnoreCase(subId, orgId);
		Optional.ofNullable(findBySubFolderIdAndOrganizationIdAllIgnoreCase).orElseThrow(() -> new Exception("No images found in ::"+ subFolder));
		return findBySubFolderIdAndOrganizationIdAllIgnoreCase;
	}
	
	public Object getContentFromFolderSubFolderNestedFolder(String company,String division,String folder,String subFolder,String nestFolder) throws Exception{
		String orgId = company+"_"+division;
		Folder folList = fRepo.findByNameAndOrganizationIdAllIgnoreCase(folder, orgId);
		Long folderId = folList.getFolderId();
		SubFolder subList = sfRepo.findBySubFolderNameAndOrganizationIdAllIgnoreCase(subFolder,orgId);
		Long subId = subList.getSubFolderId();
		NestedFolder  netfolList = nfRepo.findByNestFolderNameAndOrganizationIdAllIgnoreCase(nestFolder,orgId) ;
		Long id = netfolList.getNestedFolderId();
		List<ContentMasterDto> findByFolderIdAndSubFolderIdAndNestedFolderIdAndOrganizationIdAllIgnoreCase = cmRepo.findByFolderIdAndSubFolderIdAndNestedFolderIdAndOrganizationIdAndStatusAllIgnoreCase(folderId, subId, id, orgId,"New");
		if(findByFolderIdAndSubFolderIdAndNestedFolderIdAndOrganizationIdAllIgnoreCase.isEmpty() || findByFolderIdAndSubFolderIdAndNestedFolderIdAndOrganizationIdAllIgnoreCase == null) {
			List<ContentMasterDto> getResultList = cmRepo.findByFolderIdAndSubFolderIdAndNestedFolderIdAndOrganizationIdAndStatusAllIgnoreCase((long) 10, (long) 8, id, "CSWG_CS","New");

		}
//		Optional.ofNullable(findByFolderIdAndSubFolderIdAndNestedFolderIdAndOrganizationIdAllIgnoreCase).orElseThrow(() -> new Exception("No images found in ::"+ subFolder));
		return findByFolderIdAndSubFolderIdAndNestedFolderIdAndOrganizationIdAllIgnoreCase;
	}
	
	public Object getContentForEcomm(String tenant,String storeId,String folder) {
		String ten = "TENANT_"+ tenant;
		System.out.println("ten"+ ten);
		String store = "STORE_" + storeId;
		List<ContentMasterDto> content = cmRepo.findByFolderNameAndSubFolderSubFolderNameAndNestedFolderNestFolderNameAndStatusAllIgnoreCase(ten, store, folder, "New");
		if(content.isEmpty() || content == null) {
			List<ContentMasterDto> getResultList = cmRepo.findByFolderIdAndSubFolderIdAndNestedFolderIdAndOrganizationIdAndStatusAllIgnoreCase((long) 10, (long) 8,(long) 4, "CSWG_CS","New");
			return getResultList;
		}else {
			return content;
		}
		 
		
	}

}
