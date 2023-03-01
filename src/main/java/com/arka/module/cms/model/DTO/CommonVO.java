package com.arka.module.cms.model.DTO;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor	
public class CommonVO {
	
	private String type;
	private Long userId;
	private Date fromDate;
	private Date toDate;
	private String searchString;



}
