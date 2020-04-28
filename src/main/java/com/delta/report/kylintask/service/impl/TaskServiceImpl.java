package com.delta.report.kylintask.service.impl;

import com.delta.report.kylintask.commons.ServerResponse;
import com.delta.report.kylintask.dao.TaskDao;
import com.delta.report.kylintask.dto.KylinInfo;
import com.delta.report.kylintask.entity.Task;
import com.delta.report.kylintask.exception.TaskException;
import com.delta.report.kylintask.job.BuildCubeJob;
import com.delta.report.kylintask.service.TaskService;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDao taskDao;
    @Autowired
    private Scheduler scheduler;

    @Override
    public ServerResponse<List<Task>> getTasks(KylinInfo kylinInfo) {
        try {
            Optional<String> first = scheduler.getJobGroupNames().stream()
                    .filter(k -> k.equals(String.valueOf(kylinInfo.hashCode()))).findFirst();
            Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.groupEquals(first.get()));

            for (JobKey jobKey : jobKeys) {
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);


            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ServerResponse<Task> addTask(Task task) {
        return null;
    }

    private String getJobName(KylinInfo kylinInfo, Task task){
        return String.format("%s_%s", String.valueOf(kylinInfo.hashCode()), String.valueOf(task.hashCode()));
    }

    private String getJobGroupName(KylinInfo kylinInfo){
        return String.valueOf(kylinInfo.hashCode());
    }

    @Override
    public ServerResponse<Task> updateTask(Task task) {
        return null;
    }

    @Override
    public ServerResponse<List<Task>> deleteTask(List<Task> tasks) {
        return null;
    }

    @Override
    public ServerResponse<Task> pauseTask(Task task) {
        return null;
    }
}
