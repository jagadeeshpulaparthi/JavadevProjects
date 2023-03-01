package com.arka.module.cms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arka.module.cms.dto.ContentTypeDto;
import com.arka.module.cms.entity.ContentType;
import com.arka.module.cms.entity.UserMaster;


@Repository
public interface ContentTypeRepo extends JpaRepository <ContentType, Long>{
	boolean existsByType(String name);
	List < ContentType > findByUserIdAndMarkForDeletionAndOrganizationIdOrderByCreatedOnDesc(long id,Integer Mark_for_deletion,String oId);
	List < ContentType > findByUserIdAndTypeId(long id,long deptid);
	List <ContentType> findByTypeId(long id);
	List <ContentTypeDto> findByUserIdAndDepartmentIdAndCategoryIdOrderByCreatedOnDesc(Long uid,Long did,Long catid);
}
