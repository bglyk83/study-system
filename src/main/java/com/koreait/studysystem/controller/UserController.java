package com.koreait.studysystem.controller;

import com.koreait.studysystem.entity.User;
import com.koreait.studysystem.entity.Study;
import com.koreait.studysystem.entity.StudyApplication;
import com.koreait.studysystem.service.UserService;
import com.koreait.studysystem.service.StudyService;
import com.koreait.studysystem.service.StudyApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StudyService studyService;
    @Autowired
    private StudyApplicationService studyApplicationService;

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {
        userService.register(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @GetMapping("/mypage")
    public String mypage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);
        // 내가 만든 스터디는 findByCreatorId로 조회
        List<Study> myStudies = studyService.findByCreatorId(user.getId());
        // 각 Study에 creatorName 세팅
        for (Study study : myStudies) {
            User creator = userService.findById(study.getCreatorId());
            if (creator != null) {
                study.setCreatorName(creator.getName());
            }
        }
        List<StudyApplication> myApplications = studyApplicationService.findByUserId(user.getId());
        for (StudyApplication app : myApplications) {
            Study study = studyService.findById(app.getStudyId());
            if (study != null) {
                try {
                    java.lang.reflect.Field f = app.getClass().getDeclaredField("studyTitle");
                    f.setAccessible(true);
                    f.set(app, study.getTitle());
                } catch (Exception ignored) {}
                // 생성자 이름 세팅
                try {
                    java.lang.reflect.Field f2 = app.getClass().getDeclaredField("creatorName");
                    f2.setAccessible(true);
                    // 생성자 이름을 UserService로 조회
                    com.koreait.studysystem.entity.User creator = userService.findById(study.getCreatorId());
                    if (creator != null) {
                        f2.set(app, creator.getName());
                    }
                } catch (Exception ignored) {}
            }
        }
        model.addAttribute("user", user);
        model.addAttribute("myStudies", myStudies);
        model.addAttribute("myApplications", myApplications);
        return "mypage";
    }
} 