package com.arka.module.cms.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor		
public class TenantOnboard {
	
	private String tenantID;
	private String divisionName;
	private String tenantEmail;
	private String store;
	private String password;

}
