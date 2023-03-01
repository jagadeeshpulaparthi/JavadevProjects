package com.arka.module.cms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arka.module.cms.entity.ContentMaster;
import com.arka.module.cms.model.DTO.ContentDTO;

@Repository

public interface ContentMasterRepo extends JpaRepository <ContentMaster, Long>{
	
	List <ContentMaster> findByDocId(Long id  );
	List <ContentMaster> findByStatusAndFolderIdOrderByCreatedOnDesc(String status,Long fId);
	List <ContentMaster> findByFolderIdAndUserIdAndStatus(Long fId,Long uId,String status);
	List <ContentMaster> findByUserIdAndFolderId(Long uId,Long fId);
	List <ContentMaster> findByUserId(Long uId);
	List <ContentMaster> findByDocumentId(String docId);
	ContentMaster findByUserIdAndDocId(Long uId,long dId );
	ContentMaster findByUserIdAndFolderIdAndDocIdAndStatus(Long uId,Long fId,Long dId,String stat);
	List <ContentMasterDto> findByFolderIdAndStatusOrderByCreatedOnDesc(Long fId,String status);
	List <ContentMasterDto> findByFolderIdAndSubFolderIdAndStatusOrderByCreatedOnDesc(Long fId,Long sId,String status);
	List <ContentMasterDto> findByFolderIdAndSubFolderIdAndNestedFolderIdAndStatusOrderByCreatedOnDesc(Long folderId,Long subFolderId,Long nestedFolderId,String status);
	List <ContentMasterDto> findByUserIdAndFolderIdAndStatusAndOrganizationIdOrderByCreatedOnDesc(Long uId,Long fId,String status,String organizationId);
	List <ContentMasterDto> findByUserIdAndFolderIdAndSubFolderIdAndStatusAndOrganizationIdOrderByCreatedOnDesc(Long uId,Long fId,Long sfId,String status,String oId);
	List <ContentMasterDto> findByUserIdAndFolderIdAndSubFolderIdAndNestedFolderIdAndStatusAndOrganizationIdOrderByCreatedOnDesc(Long uId,Long fId,Long sfId,Long nfId,String Status,String oId);
	List <ContentDto> findByNestedFolderIdAndStatusAndOrganizationIdAllIgnoreCase(Long nId,String i,String oId);
	//@Query ("select new com.arka.module.cms.model.DTO.ContentDTO(cm.documentLink,cm.content) from ContentMaster cm where cm.folderId in (:id) and lower(cm.organizationId) in (:oId)")
	List <ContentMasterDto> findByFolderIdAndMarkForDeletionAndOrganizationIdAllIgnoreCase(Long id,int del,String oId);
	List <ContentMasterDto> findBySubFolderIdAndOrganizationIdAllIgnoreCase(Long id,String oId);
	List <ContentMasterDto> findByFolderIdAndSubFolderIdAndNestedFolderIdAndOrganizationIdAndStatusAllIgnoreCase(Long fId,Long sId,Long nId,String oId,String status);
	List <ContentMasterDto> findByFolderNameAndSubFolderSubFolderNameAndNestedFolderNestFolderNameAndStatusAllIgnoreCase(String folder,String subFolder,String nestFolder,String status );
	}
