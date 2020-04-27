package com.delta.report.kylintask.entity;

import com.delta.report.kylintask.commons.KylinStatus;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class Segment {
    private String uuid;
    private String name;
    private String status;
    private Timestamp dateRangeStart;
    private Timestamp dateRangeEnd;
    private Integer sourceOffsetStart;
    private Integer sourceOffsetEnd;
}
