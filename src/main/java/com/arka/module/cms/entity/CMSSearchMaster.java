package com.arka.module.cms.entity;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;
@Data
@Entity(name= "CmsSearchMaster")
@Table(name = "cms_search_master")


public class CMSSearchMaster {
	


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long Id;
	
	@Column(name = "search_field")
	private String searchField;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@CreationTimestamp
	@Column(name = "created_on")
	private Date createdOn;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@UpdateTimestamp
	@Column(name = "modified_on")
	private Date modifiedOn;
	
	
}


