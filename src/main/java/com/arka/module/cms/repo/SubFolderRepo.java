package com.arka.module.cms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.arka.module.cms.dto.FolderDetailsDto;
import com.arka.module.cms.dto.SubFolderDto;
import com.arka.module.cms.entity.SubFolder;

@Repository
public interface SubFolderRepo extends JpaRepository<SubFolder, Long> {
	List<SubFolder> findByFolderIdAndUserIdAndMarkForDeletionAndOrganizationIdOrderByCreatedOnDesc(Long fId,Long uId,Integer del,String oId);
	List<SubFolder> findBySubFolderIdAndUserId(Long sfId,Long uId);
	List<SubFolder> findBySubFolderId(Long id);
	List<SubFolder> findByMarkForDeletionAndFolderIdOrderByCreatedOnDesc(Integer del,Long fId);
	SubFolder findBySubFolderNameAndOrganizationIdAllIgnoreCase(String sfName,String oId);
	
	
	@Query("select new com.arka.module.cms.dto.SubFolderDto(sf.subFolderId,sf.subFolderName,u.userName) from  SubFolder sf INNER JOIN Folder f on f.folderId=sf.folderId INNER JOIN UserMaster u on sf.userId = u.userId INNER JOIN  UsersGroup ug on ug.userGroupId=u.userGroupId INNER JOIN AuthGroupMaster ag on ag.authGroupId = ug.authGroupId where u.userGroupId=:userGroupId and ag.authName=:authName and ag.view= true")
	List<SubFolderDto> retriveSubFolders(long userGroupId,String authName);

}
