package com.delta.report.kylintask.commons;

import com.delta.report.kylintask.dto.KylinInfo;
import com.delta.report.kylintask.entity.Task;

public class ScheduleJobUtils {
    public static String getJobName(Task task){
        return task.getTaskName();
    }
    public static String getGroupName(KylinInfo kylinInfo){
        return kylinInfo.getHost();
    }
    public static String getTriggerName(Task task){
        return task.getTaskName();
    }
}
