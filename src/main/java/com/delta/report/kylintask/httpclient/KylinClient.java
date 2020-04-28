package com.delta.report.kylintask.httpclient;

import com.delta.report.kylintask.entity.Cube;
import com.delta.report.kylintask.entity.KylinJob;
import com.delta.report.kylintask.entity.Project;
import com.delta.report.kylintask.entity.Task;
import okhttp3.Callback;

import java.util.List;

public interface KylinClient {
    String connect() throws Exception;
    List<Project> getProjects() throws Exception;
    List<Cube> getCubes(String projectName);
    Cube getCube(Cube cube);
    void buildCube(Cube cube, Task task, Callback callback) throws Exception;
    void resumeJob(KylinJob job, Callback callback) throws Exception;
    KylinJob getJob(KylinJob job, Callback callback);
}
