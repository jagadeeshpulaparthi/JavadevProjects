package com.arka.module.cms.entity;

import java.io.Serializable;
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
@Entity
@Table(name = "cms_division_master")
public class DivisionMaster implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long divisionId;
	
	@Column(name = "division_name")
	private String divisionName;
	
	@Column(name = "client_name")
	private String clientId;	
	
	@Column(name = "organization_id")
	private String organizationId;

	@Column(name = "created_role")
	private String createdRole;
	
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
	
	@Column(name = "mark_for_deletion")
	@ColumnDefault("0")
	private int markForDeletion;
	

}
