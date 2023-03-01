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
@Table(name = "content_check")
public class ContentCheck {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serialid")
	private long serialId;
	
	@Column(name = "doc_id")
	private long docId;
	
	
	@Column(name = "checked_date")
	@CreationTimestamp
	private Date checkedDate;
	
	@Column(name = "check_status")
	private String checkStatus;
	
	@Column(name = "checked_by")
	private String checkedBy;
	
	@ColumnDefault("false")
	@Column(name = "is_uploaded")
	private boolean isUploaded;
	
	@Column(name = "user_id",insertable = false,updatable = false)
	private Long userId;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "created_on")
	@CreationTimestamp
	private Date createdOn;
	
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_on")
	@UpdateTimestamp
	private Date modifiedOn;
	
	@Column(name = "mark_for_deletion")
	@ColumnDefault("0")
	private int markForDeletion;
	
	@Column(name = "organization_id")
	private String organizationId;
	
	@ManyToOne
	@JoinColumn(name = "organization_id",referencedColumnName = "organization_id",insertable = false,updatable = false)
	private DivisionMaster division;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserMaster user;

	
}
