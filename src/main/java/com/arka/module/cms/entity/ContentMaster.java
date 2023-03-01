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
@Entity(name = "ContentMaster")
@Table(name = "content_master")
public class ContentMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "doc_id")
	private Long docId;
	
	//@NotNull
	@Column(name = "name",unique = true)
	private String name; 
	
	@Column(name = "user_id",insertable = false,updatable= false)
	private Long userId;
	
	@Column(name = "folder_id",insertable = false,updatable= false)
	private Long folderId;
	
	@Column(name = "sub_folderid")
	private Long subFolderId;
	
	@Column(name = "nestedfolder_id")
	private Long nestedFolderId;
	
	@Column(name = "content")
	private String content;
	
	@Column(name  = "filetype")
	private String fileType;
	
	@Column(name = "organization_id")
	private String organizationId;
	
	@ManyToOne
	@JoinColumn(name = "organization_id",referencedColumnName = "organization_id",insertable = false,updatable = false)
	private DivisionMaster division;
	
	@ColumnDefault("0")
	@Column(name = "version")
	private float version;
	
	@Column(name= "size")
	private String size;
	
	@Column(name= "owner")
	private String owner;
	
	@Column(name= "comments")
	private String comments;
	
	@Column(name = "link")
	private String documentLink;
	
	@Column(name= "uuid")
	private String randomUuid = UUID.randomUUID().toString();
	
	@Column(name= "location")
	private String location;
	
	@Column(name= "authorize")
	@ColumnDefault("Confidential")
	private String authorization;
	
	@Column(name= "access")
	@ColumnDefault("Private")
	private String access;
	
	@Column(name= "reference_id")
	private String referenceId;
	
	@Column(name= "category_id",insertable =  false,updatable = false)
	private Long categoryId;
	
	@Column(name= "department_id",insertable =  false,updatable = false)
	private Long departmentId;
	
	@Column(name = "status")
	@ColumnDefault("New")
	private String status;
	
	@Column(name= "expirydate")
	private Date expiryDate;
	
	@Column(name= "retentiondate")
	private Date retentionDate;
	
	@Column(name= "type_id",insertable =  false,updatable = false)
	private Long typeId;
	
	@Column(name= "prefix")
	private String prefix;
	
//	@OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "folder_id", referencedColumnName = "folder_id")
//    private Folder folder;
//	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserMaster user;
	
	@ManyToOne
    @JoinColumn(name = "folder_id")
	private Folder folder  ;
	
	@ManyToOne
    @JoinColumn(name = "sub_folderid",insertable = false,updatable = false)
	private SubFolder subFolder ;
	
	@ManyToOne
    @JoinColumn(name = "nestedfolder_id",insertable = false,updatable = false)
	private NestedFolder nestedFolder ;
	
	@ManyToOne
    @JoinColumn(name = "category_id")
    private ContentCategory catId ;
	
	@ManyToOne
	@JoinColumn(name = "department_id")
    private ContentDepartment deptId ;
	
	@ManyToOne
	@JoinColumn(name = "type_id")
    private ContentType typId  ;
	
	@Column(name = "created_by")
	private String createdBy;
	
	@Column(name = "documentid")
	private String documentId;
	
	
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
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "doc_id")
//	private ContentMaster conMaster;
	
	

}
