package com.koreait.studysystem.controller;

import com.koreait.studysystem.entity.Study;
import com.koreait.studysystem.service.StudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.koreait.studysystem.entity.User;
import com.koreait.studysystem.service.UserService;
import com.koreait.studysystem.service.StudyApplicationService;

@Controller
public class StudyController {
    @Autowired
    private StudyService studyService;
    @Autowired
    private UserService userService;
    @Autowired
    private StudyApplicationService studyApplicationService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @GetMapping("/")
    public String home(@RequestParam(defaultValue = "1") int page,
                       @RequestParam(defaultValue = "10") int size,
                       @RequestParam(required = false) String keyword,
                       Model model) {
        int offset = (page - 1) * size;
        List<Study> studies = (keyword == null || keyword.isEmpty())
                ? studyService.findAll(offset, size)
                : studyService.search(keyword, offset, size);
        
        for (Study study : studies) {
            int current = studyService.countApplications(study.getId());
            try {
                java.lang.reflect.Field f = study.getClass().getDeclaredField("currentCount");
                f.setAccessible(true);
                f.set(study, current);
            } catch (Exception ignored) {}
            // 작성자 이름 세팅
            try {
                java.lang.reflect.Field f2 = study.getClass().getDeclaredField("creatorName");
                f2.setAccessible(true);
                com.koreait.studysystem.entity.User creator = userService.findById(study.getCreatorId());
                if (creator != null) {
                    f2.set(study, creator.getName());
                }
            } catch (Exception ignored) {}
        }
        int total = (keyword == null || keyword.isEmpty())
                ? studyService.countAll()
                : studyService.countSearch(keyword);
        model.addAttribute("studies", studies);
        model.addAttribute("total", total);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword", keyword);
        return "study_list";
    }

    @GetMapping("/study/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Study study = studyService.findById(id);
        // 작성자 이름 세팅
        if (study != null) {
            try {
                java.lang.reflect.Field f2 = study.getClass().getDeclaredField("creatorName");
                f2.setAccessible(true);
                com.koreait.studysystem.entity.User creator = userService.findById(study.getCreatorId());
                if (creator != null) {
                    f2.set(study, creator.getName());
                }
            } catch (Exception ignored) {}
        }
     
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);
        
        List<com.koreait.studysystem.entity.StudyApplication> applications = studyApplicationService.findByStudyId(id); 
        List<String> participantNames = new java.util.ArrayList<>();
        int participantCount = 0;
        for (com.koreait.studysystem.entity.StudyApplication app : applications) {
            com.koreait.studysystem.entity.User participant = userService.findById(app.getUserId());
            if (participant != null) {
                participantNames.add(participant.getName());
                participantCount++;
            }
        }
        boolean alreadyApplied = false;
        if (user != null) {
            alreadyApplied = studyApplicationService.countByUserAndStudy(user.getId(), id) > 0;
        }
        boolean isFull = (participantCount >= study.getMaxMember());
        model.addAttribute("study", study);
        model.addAttribute("loginUser", user);
        model.addAttribute("participantNames", participantNames);
        model.addAttribute("participantCount", participantCount);
        model.addAttribute("alreadyApplied", alreadyApplied);
        model.addAttribute("isFull", isFull);
        return "study_detail";
    }

    @GetMapping("/study/create")
    public String createForm() {
        return "study_create";
    }

    @PostMapping("/study/create")
    public String create(Study study, Model model) {
        if (study.getMaxMember() < 2) {
            model.addAttribute("error", "모집인원은 최소 2명 이상이어야 합니다.");
            return "study_create";
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        User user = userService.findByUsername(username);
        study.setCreatorId(user.getId()); // 반드시 로그인한 유저의 id로 저장
        studyService.createStudy(study);
        return "redirect:/";
    }
} 