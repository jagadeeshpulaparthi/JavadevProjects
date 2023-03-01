package com.arka.module.cms.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arka.module.cms.entity.ContentDepartment;
import com.arka.module.cms.entity.ContentMaster;
import com.arka.module.cms.model.DTO.ContentSearchDto;

@Service
public class ContentSearchCriteria {
	@Autowired
	private EntityManager entityManager;
	
	public List<?> getContentSearch(String type,Long userId,Date fromDate,Date toDate,String key){
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<?> cq = cb.createQuery(ContentSearchDto.class);
		Root<ContentMaster> root = cq.from(ContentMaster.class);
		String id = type;
		Join<ContentMaster, ContentDepartment> dept = root.join("deptId", JoinType.LEFT);		
		List<Predicate> predicates = new ArrayList<Predicate>();		
		predicates.add(cb.equal(root.get("userId"), userId));		
		if(type.equalsIgnoreCase("dateRange")) {
			predicates.add(cb.between(root.get("createdOn"), fromDate,toDate));
			cq.where(predicates.toArray(new Predicate[predicates.size()]));
		}else if(type.equalsIgnoreCase("department")) {
			cq.where(cb.like(cb.lower(dept.get("deptName")),"%"+key.toLowerCase()+"%"),cb.equal(root.get("userId"), userId));
		}
		else {
			cq.where(cb.like(cb.lower(root.get(type)),"%"+key.toLowerCase()+"%"),cb.equal(root.get("userId"), userId));
		}
		cq.multiselect(root.get("documentId"),root.get("docId"),root.get("owner"),
				root.get("referenceId"),dept.get("deptName"),root.get("location"),root.get("fileType"),
				root.get("name"),root.get("prefix"),root.get("createdOn"),root.get("version")).orderBy(cb.desc(root.get("createdOn")));		
		List<?> result = entityManager.createQuery(cq).getResultList();
		return result;
		
	}


}
