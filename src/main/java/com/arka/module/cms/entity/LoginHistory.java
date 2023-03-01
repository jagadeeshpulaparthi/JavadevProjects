package com.arka.module.cms.entity;

import java.util.Date;
import java.util.UUID;

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
@Table(name = "login_history")
public class LoginHistory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name="session_id")
	private String sessionId = UUID.randomUUID().toString();
	
	@Column(name="status")
	private String status;
	
	@CreationTimestamp
	@Column(name="loggedin")
	private Date loggedIn;
	
	@UpdateTimestamp
	@Column(name="loggedout")
	private Date loggedOut;
	
	@CreationTimestamp
	@Column(name="sessionstart")
	private Date sessionStart;
	
	@Column(name="sessionend")
	private Date sessionEnd;
	
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="login")
	private boolean login;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "user_id",insertable = false,updatable = false)
//	private UserMaster userMaster;
	

}
