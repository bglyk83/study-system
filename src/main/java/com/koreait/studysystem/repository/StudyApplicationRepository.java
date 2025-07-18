package com.koreait.studysystem.repository;

import com.koreait.studysystem.entity.StudyApplication;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudyApplicationRepository {
    void insertApplication(StudyApplication application);
    int countByUserAndStudy(@Param("userId") Long userId, @Param("studyId") Long studyId);
    List<StudyApplication> findByUserId(@Param("userId") Long userId);
    List<StudyApplication> findByStudyId(@Param("studyId") Long studyId);
} 