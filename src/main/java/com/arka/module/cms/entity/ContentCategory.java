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

/**
 * @author acer
 *
 */
/**
 * @author acer
 *
 */
@Data
@Entity(name = "content_category")
@Table(name = "content_category")

public class ContentCategory {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "category_id")
	private Long categoryId;
	
	@Column(name = "department_id", insertable = false, updatable = false)
	private Long deptId;
	
	@Column(name = "user_id", insertable = false, updatable = false)
	private Long userId;
	//@NotNull
	@Column(name = "category_name",unique = true)
	private String categoryName;
	
	@Column(name = "organization_id")
	private String organizationId;
	
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

	@ManyToOne
    @JoinColumn(name = "user_id")
    private UserMaster user ;
	
	@ManyToOne
    @JoinColumn(name = "department_id")
    private ContentDepartment dept ;
	
	@ManyToOne
	@JoinColumn(name = "organization_id",referencedColumnName = "organization_id",insertable = false,updatable = false)
	private DivisionMaster division;


}
