package com.arka.module.cms.entity;

import java.util.Date;
import java.util.UUID;

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
@Table(name = "subfolder")
public class SubFolder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sub_folderid")
	private Long subFolderId;
	
	@Column(name = "subfolder_name")
	private String subFolderName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "folder_id")
	private Long folderId;
	
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "uuid")
	private String subFolderUuid = UUID.randomUUID().toString();

	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "organization_id")
	private String organizationId;
	
	@ManyToOne
	@JoinColumn(name = "organization_id",referencedColumnName = "organization_id",insertable = false,updatable = false)
	private DivisionMaster division;
	
	@Column(name = "created_on")
	@CreationTimestamp
	private Date createdOn;
	
	@Column(name = "mark_for_deletion")
	@ColumnDefault("0")
	private Integer  markForDeletion;
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_on")
	@UpdateTimestamp
	private Date modifiedOn;
	
	@ManyToOne
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    private UserMaster user;
	
	@ManyToOne
    @JoinColumn(name = "folder_id",insertable = false,updatable = false)
    private Folder folder;
}
