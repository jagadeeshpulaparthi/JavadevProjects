package com.arka.module.cms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arka.module.cms.entity.ContentDepartment;
import com.arka.module.cms.entity.UserMaster;

@Repository
public interface ContentDepartmentRepo extends JpaRepository <ContentDepartment, Long>{
	 	boolean existsByDeptName(String name);
		List < ContentDepartment > findByUserIdAndMarkForDeletionAndOrganizationId(long id,Integer Mark_for_deletion,String oId);
		List < ContentDepartment > findByUserIdAndDeptId(long id,long deptid);
		List <ContentDepartment> findByDeptId(long id);


}
