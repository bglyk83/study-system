package com.koreait.studysystem.service;

import com.koreait.studysystem.entity.StudyApplication;
import com.koreait.studysystem.repository.StudyApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyApplicationServiceImpl implements StudyApplicationService {
    @Autowired
    private StudyApplicationRepository studyApplicationRepository;

    @Override
    public void apply(StudyApplication application) {
        studyApplicationRepository.insertApplication(application);
    }

    @Override
    public int countByUserAndStudy(Long userId, Long studyId) {
        return studyApplicationRepository.countByUserAndStudy(userId, studyId);
    }

    @Override
    public List<StudyApplication> findByUserId(Long userId) {
        return studyApplicationRepository.findByUserId(userId);
    }

    @Override
    public List<StudyApplication> findByStudyId(Long studyId) {
        return studyApplicationRepository.findByStudyId(studyId);
    }
} 