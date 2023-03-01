package com.arka.module.cms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity(name="number_range")
@Table(name="number_range")

public class NumberRange {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sn_no")
	private Long serialNo;
	
	@Column(name = "id")
	private Long id;
	
	@Column(name = "type")
	String type;

}
