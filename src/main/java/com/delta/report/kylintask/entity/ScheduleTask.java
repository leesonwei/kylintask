package com.delta.report.kylintask.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class ScheduleTask {
    private String jobName;
    private String jobGroup;
    private String status;
    private Timestamp nextFireTime;
    private Timestamp preFireTime;
}
