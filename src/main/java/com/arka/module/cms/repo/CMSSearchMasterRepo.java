package com.arka.module.cms.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.arka.module.cms.entity.CMSSearchMaster;


@Repository
public interface CMSSearchMasterRepo extends JpaRepository<CMSSearchMaster, Integer>  {
	@Modifying
	@Query(value = "insert into CMSSearchMaster(referenceId,name,user,fileType,documentId,prefix,depatment) VALUES (?,?,?,?,?,?,?)", nativeQuery = true)
	@Transactional
	public int addData(@Param("referenceId")Long referenceId, @Param("name")String name, @Param("user")String user,@Param("fileType")String fileType,@Param("documentId")Long documentId,@Param("prefix")String prefix,@Param("department")String department);
}
