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
@Entity
@Table(name="content_department")

public class ContentDepartment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "department_id")
	private Long deptId;
	
	//@NotNull
	@Column(name = "dept_name")
	private String deptName;
	
		
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
	
	@Column(name = "organization_id")
	private String organizationId;
	
	@ManyToOne
	@JoinColumn(name = "organization_id",referencedColumnName = "organization_id",insertable = false,updatable = false)
	private DivisionMaster division;
	
	@Column(name = "user_id",insertable = false,updatable = false)
	private Long  userId;
	
	@Column(name = "modified_on")
	@UpdateTimestamp
	private Date modifiedOn;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private  UserMaster user ;
}
