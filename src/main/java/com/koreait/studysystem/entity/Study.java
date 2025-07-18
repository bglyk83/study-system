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
public class Study {
    private Long id;
    private String title;
    private String description;
    private int maxMember;
    private Date deadline;
    private Long creatorId;
    private Integer currentCount; 
    private String creatorName;
} 