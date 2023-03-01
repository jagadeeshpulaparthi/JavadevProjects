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
@Entity(name = "content_type")
@Table(name = "content_type")
public class ContentType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "type_id")
	private long typeId;
	
	//@NotNull
	@Column(name = "type",unique = true)
	private String type;
	
	@Column(name = "user_id",insertable = false,updatable = false )
	private long userId;
	
	@Column(name = "created_by")
	private String createdBy;
	
	
	@Column(name = "created_on")
	@CreationTimestamp
	private Date createdOn;
	
	@Column(name = "mark_for_deletion")
	@ColumnDefault("0")
	private int markForDeletion;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_on")
	@UpdateTimestamp
	private Date modifiedOn;
	
	@Column(name = "organization_id")
	private String organizationId;
	
	@ManyToOne
	@JoinColumn(name = "organization_id",referencedColumnName = "organization_id",insertable = false,updatable = false)
	private DivisionMaster division;
	
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private UserMaster user;

	@Column(name = "department_id",insertable = false,updatable = false)
	private Long departmentId;
	
	
	@Column(name = "category_id",insertable = false,updatable = false)
	private Long categoryId;

	@ManyToOne
    @JoinColumn(name = "department_id")
    private ContentDepartment department;
	
	@ManyToOne
    @JoinColumn(name = "category_id")
    private ContentCategory category;
	
}
