package com.delta.report.kylintask.entity;

import lombok.Data;

import java.util.List;

@Data
public class KylinJob {
    private String uuid;
    private String version;
    private String name;
    private String projectName;
    private String relatedCube;
    private String relatedSegment;
    private String relatedSegmene;
    private List<Step> steps;
    private boolean isResume;
    private Integer resumeTimes;
}
