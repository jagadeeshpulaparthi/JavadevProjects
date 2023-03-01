package com.arka.module.cms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arka.module.cms.entity.ContentCategory;
import com.arka.module.cms.entity.ContentDepartment;
import com.arka.module.cms.entity.UserMaster;

@Repository

public interface ContentCategoryRepo extends JpaRepository <ContentCategory, Long> {
	
	boolean  existsByCategoryName(String name);
	 List<ContentCategory > findByDeptIdAndMarkForDeletion(long l,int delete);
	 List<ContentCategory> findByCategoryName(String name);
	 List<ContentCategory> findByCategoryId(long id);
	 List<ContentCategory> findByUserIdAndMarkForDeletionAndOrganizationIdOrderByCreatedOnDesc(long id ,int delete,String oId);




}
