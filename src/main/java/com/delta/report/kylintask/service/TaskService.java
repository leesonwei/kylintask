package com.delta.report.kylintask.service;

import com.delta.report.kylintask.commons.ServerResponse;
import com.delta.report.kylintask.dto.KylinInfo;
import com.delta.report.kylintask.entity.Task;

import java.util.List;

//添加任務到數據庫
public interface TaskService {
    ServerResponse<List<Task>> getTasks(KylinInfo kylinInfo);
    ServerResponse<Task> addTask(Task task);
    ServerResponse<Task> updateTask(Task task);
    ServerResponse<List<Task>> deleteTask(List<Task> tasks);
    ServerResponse<Task> pauseTask(Task task);
}
