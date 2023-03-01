package com.arka.module.cms.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arka.module.cms.entity.ContentHistory;

@Repository
public interface ContentHistoryRepo extends JpaRepository<ContentHistory, Long> {
	List<ContentHistory> findByDocId(Long id);
	List<ContentHistory> findByRecordVersionAndDocId(float version,Long id);
}
