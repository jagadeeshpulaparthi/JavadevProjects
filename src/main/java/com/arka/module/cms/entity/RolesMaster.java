package com.arka.module.cms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="cms_role")
public class RolesMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "roleid")
	private Long roleId;
	
	@Column(name = "role")
	private String roleName;
	
	@Column(name= "createdby")
	private String createdBy;
	
	@Column(name= "updatedby")
	private String updatedBy;
	
	@CreationTimestamp
	@Column(name = "createdate")
	private Date createdDate;
	
	@Column(name = "updatedate")
	@UpdateTimestamp
	private Date updatedDate;
	
	@Column(name = "status")
	private String status;
	
}
