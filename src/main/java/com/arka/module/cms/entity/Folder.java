package com.arka.module.cms.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;





@Data
@Entity
@Table(name = "folder")
@AllArgsConstructor
@NoArgsConstructor
public class Folder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "folder_id")
	private long folderId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "is_locked")
	@ColumnDefault("False")
	private boolean isLocked;
	
	@Column(name = "is_archeived")
	@ColumnDefault("False")
	private boolean isArchive;
	
	@Column(name = "archeived_by")
	private String archeivedBy;
	
	@Column(name = "folder_uuid")
	private String folderUuid = UUID.randomUUID().toString();
	
	@Column(name = "user_id",insertable = false,updatable = false)
	private long userId;
	
	
	@Column(name = "organization_id")
	private String organizationId;
//	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "organization_id",referencedColumnName = "organization_id",insertable = false,updatable = false)
	private DivisionMaster division;
	
	@Column(name = "created_by")
	private String createdBy;
	
	
	@Column(name = "created_on")
	@CreationTimestamp
	private Date createdOn;
	
	@Column(name = "mark_for_deletion")
	@ColumnDefault("0")
	private long markForDeletion;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_on")
	@UpdateTimestamp
	private Date modifiedOn;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private UserMaster user;
}
