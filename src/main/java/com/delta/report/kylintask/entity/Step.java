package com.delta.report.kylintask.entity;

import com.delta.report.kylintask.commons.KylinStatus;
import lombok.Data;

import java.sql.Timestamp;


@Data
public class Step {
    private String id;
    private String name;
    private Timestamp execStartTime;
    private Timestamp execEndTime;
    private String stepStatus;
}
