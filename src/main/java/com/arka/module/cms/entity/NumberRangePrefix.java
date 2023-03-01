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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "numberrange_prefix")
public class NumberRangePrefix {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long prefixId;
	
	@Column(name = "prefix")
	private String prefix;
	
	@Column(name = "user_id",insertable = false,updatable = false)
	private Long userId;
	
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
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserMaster user;
	
	
	

}
