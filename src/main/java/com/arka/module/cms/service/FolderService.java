package com.arka.module.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arka.module.cms.entity.Folder;
import com.arka.module.cms.repo.FolderRepo;
import com.arka.module.cms.utils.Response;

@SuppressWarnings("unused")

@Service
public class FolderService {
	@Autowired
	FolderRepo fRepo;

	Response response ;


	public Object permanentDeleteFolder(Long userId,Long folderId,String email){
		List <Folder> folList = fRepo.findByFolderId(folderId);
		if(!folList.isEmpty()) {
			Folder folder = folList.get(0);
			folder.setMarkForDeletion(2);
			folder.setModifiedBy(email);
			folder.setName(null);
			Folder saveFolder = fRepo.save(folder);
			response = new Response(200,"Folder delted permanently");
		}else {
			response = new Response (204,"Failed to delete");
		}
		return response;
	}

	public Object countOfFolders(Long userId) {
		Long countFolder = fRepo.countByMarkForDeletionAndIsArchiveAndUserId(0, false, userId);
		response = new Response(200,"Success","Count Of Folders :: "+ countFolder);
		return response;

	}



}