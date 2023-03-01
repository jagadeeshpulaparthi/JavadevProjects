package com.arka.module.cms.model.DTO;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentSearchDto {
	private String documentId;
	private Long docId;
	private String owner;
	private String referenceId;
	private String department;
	private String location;
	private String fileType;
	private String fileName;
	private String prefix;
	private Date createdOn;
	private float version;


}
