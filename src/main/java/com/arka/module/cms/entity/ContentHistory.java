package com.arka.module.cms.entity;

import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name = "content_history")
public class ContentHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "history_id")
	private Long historyId; 
	
	@Column(name = "doc_id")
	private Long docId ;
	
	@Column(name = "folder_id",insertable = false,updatable = false)
	private Long folderId; 
	
	@Column(name = "date")
	@CreationTimestamp
	private Date date;
	
	@Column(name = "user_id",insertable = false,updatable = false)
	private Long userId ;
	
	@Column(name = "recordversion")
	private float recordVersion;
	
	@Column(name = "new_name")
	private String newName; 
	
	@ColumnDefault("false")
	@Column(name = "isversionchange")
	private boolean versionChange ;
	
	@Column(name = "modified_content")
	private String modifiedContent;
	
	
	
	@Column(name = "prefix")
	private String prefix;
	
	@Column(name = "file_type")
	private String fileType;
	
	@Column(name = "size")
	private String  size ;
	
	@Column(name = "location")
	private String location; 
	
	@Column(name = "category_id",insertable = false,updatable = false)
	private Long categoryId ;
	
	@Column(name = "dept_id",insertable = false,updatable = false)
	private Long deptId ;
	
	@Column(name = "type_id",insertable = false,updatable = false)
	private Long typeId ;
	
	@Column(name = "retention_date")
	private Date retentionDate;
	
	@Column(name = "expiry_date")
	private Date expiryDate ;
	
	@Column(name = "uuid")
	private String uuid;
	
	@Column(name = "status")
	@ColumnDefault("New")
	private String status; 
	
	@Column(name = "access")
	@ColumnDefault("Private")
	private String access ;
	
	@Column(name = "authorize")
	@ColumnDefault("Confidential")
	private String authorize ;
	
	@Column(name = "sub_folderid")
	private Long subFolderId;
	
	@Column(name = "nestedfolder_id")
	private Long nestedFolderId;
	
	@Column(name = "organization_id")
	private String organizationId;
	
	@ManyToOne
	@JoinColumn(name = "organization_id",referencedColumnName = "organization_id",insertable = false,updatable = false)
	private DivisionMaster division;
	
	@Column(name = "created_by")
	private String createdBy ;
	
	@Column(name = "created_on")
	@CreationTimestamp
	private Date createdOn ;
	
	
	@Column(name = "modified_by")
	private String modifiedBy ;
	
	@Column(name = "modified_on")
	@UpdateTimestamp
	private Date modifiedOn;
	
	@ColumnDefault("0")
	@Column(name = "mark_for_deletion")
	private int mark_for_deletion;  
	
	@ManyToOne
    @JoinColumn(name = "folder_id")
    private Folder folder  ;
	
	@ManyToOne
    @JoinColumn(name = "category_id")
    private ContentCategory category ;
	
	@ManyToOne
    @JoinColumn(name = "sub_folderid",insertable = false,updatable = false)
	private SubFolder subFolder  ;
	
	@ManyToOne
    @JoinColumn(name = "nestedfolder_id",insertable = false,updatable = false)
	private NestedFolder nestedFolder ;
	
	
	@ManyToOne
	@JoinColumn(name = "dept_id")
    private ContentDepartment dept ;
	
	@ManyToOne
	@JoinColumn(name = "type_id")
    private ContentType type  ;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
    private UserMaster user  ;
	
	
	

}
