package com.delta.report.kylintask.service.impl;

import com.baomidou.mybatisplus.service.IService;
import com.delta.report.kylintask.commons.ScheduleJobUtils;
import com.delta.report.kylintask.commons.ServerResponse;
import com.delta.report.kylintask.dao.TaskDao;
import com.delta.report.kylintask.dto.KylinInfo;
import com.delta.report.kylintask.dto.TaskDto;
import com.delta.report.kylintask.entity.Task;
import com.delta.report.kylintask.exception.TaskException;
import com.delta.report.kylintask.job.BuildCubeJob;
import com.delta.report.kylintask.service.TaskService;
import com.fasterxml.jackson.databind.ser.Serializers;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public ServerResponse<List<TaskDto>> getTasks(KylinInfo kylinInfo) {
        List<TaskDto> taskDtos = new ArrayList<>();
        try {
            Optional<String> first = scheduler.getJobGroupNames().stream()
                    .filter(k -> k.equals(ScheduleJobUtils.getGroupName(kylinInfo))).findFirst();
            Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.groupEquals(first.get()));

            for (JobKey jobKey : jobKeys) {
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                Optional<Trigger> trigger = (Optional<Trigger>) scheduler.getTriggersOfJob(jobKey).stream().findFirst();
                taskDtos.add(buildDto(first.get(),jobDetail,trigger));
            }
        } catch (SchedulerException e) {
            log.error(e.toString());
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
        return ServerResponse.createBySuccess(taskDtos);
    }

    private TaskDto buildDto(String group,JobDetail jobDetail,Optional<Trigger> trigger) throws SchedulerException {
        Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.get().getKey());
        TaskDto taskDto = new TaskDto();
        taskDto.setDescription(jobDetail.getDescription());
        taskDto.setStartAt(trigger.get().getStartTime());
        taskDto.setEndAt(trigger.get().getEndTime());
        taskDto.setGroup(group);
        taskDto.setNextFireTime(trigger.get().getNextFireTime());
        taskDto.setStatus(triggerState.name());
        return taskDto;
    }

    @Override
    public ServerResponse<TaskDto> addTask(Task task, KylinInfo kylinInfo) {
        JobDetail jobDetail = JobBuilder.newJob()
                .usingJobData(new JobDataMap(BeanMap.create(task)))
                .withIdentity(ScheduleJobUtils.getJobName(task), ScheduleJobUtils.getGroupName(kylinInfo))
                .withDescription(task.getDescription())
                .build();
        TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
        if (task.isLimit()) {
            triggerBuilder.startAt(task.getTaskStart()).endAt(task.getTaskEnd());
        }
        CronTrigger cronTrigger = triggerBuilder.withIdentity(ScheduleJobUtils.getJobName(task), ScheduleJobUtils.getGroupName(kylinInfo))
                .forJob(jobDetail).withSchedule(CronScheduleBuilder.cronSchedule(task.getCron()))
                .build();
        try {
            scheduler.scheduleJob(jobDetail, cronTrigger);
            return ServerResponse.createBySuccess(buildDto(ScheduleJobUtils.getGroupName(kylinInfo), jobDetail, Optional.of(cronTrigger)));
        } catch (SchedulerException e) {
            log.error(e.toString());
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
    }

    @Override
    public ServerResponse<TaskDto> updateTask(Task task) {
        return null;
    }

    @Override
    public ServerResponse<List<TaskDto>> deleteTask(Task task) {

        return null;
    }

    @Override
    public ServerResponse<TaskDto> pauseTask(Task task) {
        return null;
    }
}
