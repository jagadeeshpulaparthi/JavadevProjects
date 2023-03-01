package com.arka.module.cms.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="user_master")
public class UserMaster {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "organization_id")
	private String organizationId;
	
	@NotNull
	@Column(name = "emailid",unique = true)
	private String emailId;
	
	@Column(name = "dob")
	private String dateOfBirth;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "mobileno")
	private String mobileNo;
	
	@CreationTimestamp
	@Column(name = "created_on")
	private Date createdOn;
	
	
	@Column(name = "modified_by")
	private String modifiedBy;
	
	@Column(name = "modified_on")
	@UpdateTimestamp
	private Date modifiedOn;
	
	@Column(name = "mark_for_deletion")
	@ColumnDefault("0")
	private int markForDeletion;
	
	
	@Column(name = "roleid")
	private Integer roleId;
	
	@ManyToOne
	@JoinColumn(name = "organization_id",referencedColumnName = "organization_id",insertable = false,updatable = false)
	private DivisionMaster division;
	
	@ManyToOne
	@JoinColumn(name = "roleid",referencedColumnName = "roleid",insertable = false,updatable = false)
	private RolesMaster role;
	
	@ManyToOne
	@JoinColumn(name = "user_id",insertable = false,updatable = false)
	private LoginHistory loginHistory;
	
	@Column(name = "user_group_id")
	private long userGroupId;
	
	@Column(name = "user_type")
	private String userType;

}
