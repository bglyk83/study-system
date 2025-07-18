package com.koreait.studysystem.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudyApplication {
    private Long id;
    private Long userId;
    private Long studyId;
    private Date appliedAt;
    private String studyTitle;
    private String creatorName;
} 