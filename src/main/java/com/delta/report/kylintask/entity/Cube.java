package com.delta.report.kylintask.entity;

import com.delta.report.kylintask.commons.KylinStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Properties;

@Data
@EqualsAndHashCode(of = "uuid")
public class Cube {
    private String uuid;
    private String version;
    private String name;
    private String status;
    private List<Segment> segments;
    private String project;
    private boolean isStreaming;
    private String model;
}
