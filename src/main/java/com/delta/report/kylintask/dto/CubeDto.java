package com.delta.report.kylintask.dto;

import com.delta.report.kylintask.entity.Cube;
import com.delta.report.kylintask.entity.Segment;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Optional;


@Data
public class CubeDto extends Cube {
    private Timestamp startTime;
    private Timestamp endTime;
    private String buildType;
    private boolean isFullBuild;
    private boolean isResume;
    private Integer resumeTimes;
    private Timestamp taskStart;
    private Timestamp taskEnd;

    public boolean isFullBuild(){
        Optional<Segment> first = this.getSegments().parallelStream().findFirst();
        return first.get().getName().contains("FULL_BUILD");
    }
}
