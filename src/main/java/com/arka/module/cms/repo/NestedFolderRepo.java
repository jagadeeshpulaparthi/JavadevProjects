package com.arka.module.cms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arka.module.cms.entity.NestedFolder;

@Repository
public interface NestedFolderRepo extends JpaRepository<NestedFolder, Long>{
	List<NestedFolder> findByFolderIdAndSubFolderIdAndUserIdAndMarkForDeletionAndOrganizationIdOrderByCreatedOnDesc(Long fId,Long sId,Long uId,Integer del,String oId);
	List<NestedFolder> findByNestedFolderId(Long nId);
	List<NestedFolder> findByMarkForDeletionAndFolderIdAndSubFolderIdOrderByCreatedOnDesc(Integer del,Long fId,Long sId);
	NestedFolder findByNestFolderNameAndOrganizationIdAllIgnoreCase(String fName,String oId);
	
}
