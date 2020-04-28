package com.delta.report.kylintask.controller;

import com.delta.report.kylintask.commons.ServerResponse;
import com.delta.report.kylintask.dto.KylinInfo;
import com.delta.report.kylintask.dto.TaskDto;
import com.delta.report.kylintask.entity.Cube;
import com.delta.report.kylintask.entity.Task;
import com.delta.report.kylintask.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Action;
import java.util.List;

@RestController
@RequestMapping("tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("")
    public ServerResponse<List<TaskDto>> getTask(KylinInfo kylinInfo){
        return taskService.getTasks(kylinInfo);
    }

    @PutMapping("/add")
    public ServerResponse<TaskDto> getTask(KylinInfo kylinInfo, Task task){
        return taskService.addTask(task, kylinInfo);
    }
}
