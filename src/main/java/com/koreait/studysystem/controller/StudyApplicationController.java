package com.koreait.studysystem.controller;

import com.koreait.studysystem.entity.StudyApplication;
import com.koreait.studysystem.entity.User;
import com.koreait.studysystem.service.StudyApplicationService;
import com.koreait.studysystem.service.StudyService;
import com.koreait.studysystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class StudyApplicationController {
    @Autowired
    private StudyApplicationService studyApplicationService;
    @Autowired
    private StudyService studyService;
    @Autowired
    private UserService userService;

    @PostMapping("/study/{id}/apply")
    public String apply(@PathVariable Long id, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);

        // 중복 신청 체크
        if (studyApplicationService.countByUserAndStudy(user.getId(), id) > 0) {
            return "redirect:/study/" + id + "?error=duplicate";
        }
        // 정원 초과 체크
        int current = studyService.countApplications(id);
        int max = studyService.findById(id).getMaxMember();
        if (current >= max) {
            return "redirect:/study/" + id + "?error=full";
        }

        StudyApplication application = new StudyApplication();
        application.setUserId(user.getId());
        application.setStudyId(id);
        studyApplicationService.apply(application);
        return "redirect:/mypage";
    }
} 