package com.arka.module.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FolderDetailsDto {

	private long folderId;
	private String name;
	private String userName;
	//private Boolean view=false;
	
	
}
