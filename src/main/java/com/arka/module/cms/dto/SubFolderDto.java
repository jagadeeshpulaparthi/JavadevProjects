package com.arka.module.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubFolderDto {
	
	private Long subFolderId;
	private String subFolderName;
	private String userName;
	//private String view;

}
