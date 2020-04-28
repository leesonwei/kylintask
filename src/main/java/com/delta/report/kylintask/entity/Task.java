package com.delta.report.kylintask.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;


@Data
@EqualsAndHashCode(of = "taskName")
public class Task {
    private String taskName;
    private Cube cube;
    private String segment;
    private String startTime;
    private String endTime;
    private String buildType;
    private String cron;
    private boolean isFullBuild;
    private boolean isResume;
    private Integer resumeTimes;
    private boolean isLimit;
    private Timestamp taskStart;
    private Timestamp taskEnd;
    private String status;
    private boolean isDone;
}
