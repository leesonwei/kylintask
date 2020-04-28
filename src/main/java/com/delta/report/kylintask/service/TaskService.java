package com.delta.report.kylintask.service;

import com.delta.report.kylintask.commons.ServerResponse;
import com.delta.report.kylintask.dto.KylinInfo;
import com.delta.report.kylintask.dto.TaskDto;
import com.delta.report.kylintask.entity.Task;

import java.util.List;

//添加任務到數據庫
public interface TaskService {
    ServerResponse<List<TaskDto>> getTasks(KylinInfo kylinInfo);
    ServerResponse<TaskDto> addTask(Task task, KylinInfo kylinInfo);
    ServerResponse<TaskDto> updateTask(Task task);
    ServerResponse<List<TaskDto>> deleteTask(Task task);
    ServerResponse<TaskDto> pauseTask(Task task);
}
