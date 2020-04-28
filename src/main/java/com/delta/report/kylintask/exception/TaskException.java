package com.delta.report.kylintask.exception;

import com.delta.report.kylintask.dto.KylinError;
import com.delta.report.kylintask.entity.Task;

public class TaskException extends RuntimeException {
    private static final long serialVersionUID = -8102677845935010551L;

    public TaskException() {
        super();
    }

    public TaskException(String message) {
        super(message);
    }

    public TaskException(Task task) {
        super(String.format("Task  exception: %s", task.toString()));
    }
}
