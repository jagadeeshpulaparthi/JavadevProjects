package com.arka.module.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arka.module.cms.entity.PermissionMaster;

@Repository
public interface PermissionMasterRepo extends JpaRepository<PermissionMaster,Long> {

}
