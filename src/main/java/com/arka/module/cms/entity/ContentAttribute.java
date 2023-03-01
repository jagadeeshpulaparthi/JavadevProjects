package com.arka.module.cms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity(name= "content_attribute")
@Table(name= "content_attribute")
public class ContentAttribute {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "doc_id")
	private Long docId;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "attr_status")
	private String attrStatus;
	
	@Column(name = "attr_changedby")
	private String attrBy;
	
	@Column(name = "attr_changeddate")
	@CreationTimestamp
	private Date attrCreatedDate;
	
	@Column(name = "lockuser")
	private String lockUser;
	
	@Column(name = "modified_by" )
	private String modifiedBy;
	
	@Column(name = "modified_on")
	@UpdateTimestamp
	private Date modifiedOn;
	
	@Column(name = "mark_for_deletion")
	@ColumnDefault("0")
	private Integer markForDelete;
	
	@Column(name = "organization_id")
	private String organizationId;
	
	@ManyToOne
	@JoinColumn(name = "organization_id",referencedColumnName = "organization_id",insertable = false,updatable = false)
	private DivisionMaster division;

	
}
