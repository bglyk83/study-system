package com.koreait.studysystem.repository;

import com.koreait.studysystem.entity.Study;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudyRepository {
    void insertStudy(Study study);
    Study findById(@Param("id") Long id);
    List<Study> findAll(@Param("offset") int offset, @Param("limit") int limit);
    List<Study> search(@Param("keyword") String keyword, @Param("offset") int offset, @Param("limit") int limit);
    int countAll();
    int countSearch(@Param("keyword") String keyword);
    int countApplications(@Param("studyId") Long studyId);
    List<Study> findByCreatorId(@Param("creatorId") Long creatorId);
} 