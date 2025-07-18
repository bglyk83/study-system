package com.koreait.studysystem.service;

import com.koreait.studysystem.entity.Study;
import java.util.List;

public interface StudyService {
    void createStudy(Study study);
    Study findById(Long id);
    List<Study> findAll(int offset, int limit);
    List<Study> search(String keyword, int offset, int limit);
    int countAll();
    int countSearch(String keyword);
    int countApplications(Long studyId);
    List<Study> findByCreatorId(Long creatorId);
} 