package com.arka.module.cms.repo;

import java.util.List;

public interface ContentMasterDto {
	String getName();
	Long  getDocId();
	String getStatus();
	String getOwner();
	float getVersion();
	String getSize();
	String getDocumentId();
	String getComments();
	String getDocumentLink();
	String getContent();
//	List getFolder();

}
