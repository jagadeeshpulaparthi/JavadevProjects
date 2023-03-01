package com.arka.module.cms.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arka.module.cms.entity.UsersGroup;

@Repository
public interface UsersGroupRepo extends JpaRepository<UsersGroup, Long>{

}
