package com.arka.module.cms.repo;

import java.util.List;

public interface ContentDto {
	
	Long  getDocId();
	String getName();
	String getStatus();
	String getOwner();
	float getVersion();
	String getSize();
	String getDocumentId();
	String getComments();
	String getDocumentLink();
	String getContent();
	List getFolder();
	List getSubFolder();
	List getNestedFolder();

}
