package com.koreait.studysystem.service;

import com.koreait.studysystem.entity.StudyApplication;
import java.util.List;

public interface StudyApplicationService {
    void apply(StudyApplication application);
    int countByUserAndStudy(Long userId, Long studyId);
    List<StudyApplication> findByUserId(Long userId);
    List<StudyApplication> findByStudyId(Long studyId);
} 