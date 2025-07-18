package com.koreait.studysystem.service;

import com.koreait.studysystem.entity.Study;
import com.koreait.studysystem.repository.StudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyServiceImpl implements StudyService {
    @Autowired
    private StudyRepository studyRepository;

    @Override
    public void createStudy(Study study) {
        studyRepository.insertStudy(study);
    }

    @Override
    public Study findById(Long id) {
        return studyRepository.findById(id);
    }

    @Override
    public List<Study> findAll(int offset, int limit) {
        return studyRepository.findAll(offset, limit);
    }

    @Override
    public List<Study> search(String keyword, int offset, int limit) {
        return studyRepository.search(keyword, offset, limit);
    }

    @Override
    public int countAll() {
        return studyRepository.countAll();
    }

    @Override
    public int countSearch(String keyword) {
        return studyRepository.countSearch(keyword);
    }

    @Override
    public int countApplications(Long studyId) {
        return studyRepository.countApplications(studyId);
    }

    @Override
    public List<Study> findByCreatorId(Long creatorId) {
        return studyRepository.findByCreatorId(creatorId);
    }
} 