package com.arka.module.cms.repo;

import java.util.List;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arka.module.cms.dto.FolderDetailsDto;
import com.arka.module.cms.entity.Folder;

@Repository

public interface FolderRepo extends JpaRepository <Folder, Long> {
	boolean existsByName(String name);
	List <Folder> findByCreatedByAndMarkForDeletionAndIsArchiveOrderByCreatedOnDesc(String id,long active,boolean st);
	List<Folder> findByFolderId(Long id);
	List <Folder> findByUserIdAndIsArchive(Long uid,boolean st);
	List <Folder> findByUserIdAndMarkForDeletion(Long uId,Long del);
	List <Folder> findByUserIdAndMarkForDeletionAndIsArchiveAndOrganizationIdOrderByCreatedOnDesc(Long id,long active,boolean st,String oId);
	Folder findByNameAndOrganizationIdAllIgnoreCase(String name ,String oId);
	Folder findByUserIdAndOrganizationIdAndNameAllIgnoreCase(Long uId,String oId,String name);
	List <Folder> findByMarkForDeletionAndIsArchiveOrderByCreatedOnDesc(long active,boolean st);
	Long countByMarkForDeletionAndIsArchiveAndUserId(long del,boolean status,Long id);
//	@Query("from folder where folder_id in (:ids)")
//	List<Folder> findByFolderId(List<Integer> ids);
//	@Transactional
//	@Query(value = " select f.folder_id,f.name,u.username from folder f \r\n"
//			+ "inner join user_master u on f.user_id=u.user_id\r\n"
//			+ "inner join users_group ug on ug.user_group_id = u.user_group_id\r\n"
//			+ "inner join auth_group_master ag on ag.auth_group_id = ug.auth_group_id\r\n"
//			+ "where u.user_group_id=:userGroupId and ag.view= true and ag.auth_name=:authName", nativeQuery = true)
//	List<Object> listFolders(long userGroupId,String authName);
	
	@Query("select new com.arka.module.cms.dto.FolderDetailsDto(f.folderId,f.name,u.userName) from Folder f INNER JOIN UserMaster u on f.userId = u.userId where f.markForDeletion=0 and isArchive= false order by f.createdOn desc")
	List<FolderDetailsDto> findAllActiveFolders();
	
//	@Modifying
//	@Query("select new com.arka.module.cms.dto.FolderDetailsDto(f.folderId,f.name,u.userName) from Folder f INNER JOIN UserMaster u on f.userId = u.userId INNER JOIN  UsersGroup ug on ug.userGroupId=u.userGroupId INNER JOIN AuthGroupMaster ag on ag.authGroupId = ug.authGroupId where u.userGroupId=:userGroupId and ag.authName=:authName and ag.view= true")
	@Query("select sf.sub_folderid, sf.subfolder_name, u.username,ag.view from subfolder as sf inner join folder f on f.folder_id = sf.folder_id inner join user_master u on sf.user_id=u.user_id inner join users_group ug on ug.user_group_id = u.user_group_id inner join auth_group_master ag on ag.auth_group_id = ug.auth_group_id where u.user_group_id=1 and ag.view= true and ag.auth_name='sub_folder'")
	List<FolderDetailsDto> retriveFolders(long userGroupId,String authName);

	



}
