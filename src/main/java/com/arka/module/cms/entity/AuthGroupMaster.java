package com.arka.module.cms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

import lombok.Data;

@Data
@Entity
@Table(name="auth_group_master")
public class AuthGroupMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "auth_group_id")
	private long authGroupId;
	
	@Column(name = "auth_name")
	private String authName;
	
	@Column(name = "insert")
	private boolean create;
	
	@Column(name = "view")
	private boolean view;
	
	@Column(name = "update") 
	private boolean update;
	
	@Column(name = "delete") 
	private boolean delete;
	
//	@Type(type = "json")
//	 @Column(columnDefinition = "json")
//	 private Object authPermissions; 

	@Column(name = "auth_object_name")
	private String authObjectName; 

}
