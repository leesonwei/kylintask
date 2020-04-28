package com.delta.report.kylintask.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TaskDto {
    private String name;
    private String group;
    private Date startAt;
    private Date endAt;
    private Date nextFireTime;
    private String status;
    private String description;
}
