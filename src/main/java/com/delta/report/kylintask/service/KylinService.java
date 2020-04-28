package com.delta.report.kylintask.service;

import com.delta.report.kylintask.commons.ServerResponse;
import com.delta.report.kylintask.entity.Cube;
import com.delta.report.kylintask.dto.KylinInfo;
import com.delta.report.kylintask.entity.KylinJob;
import com.delta.report.kylintask.entity.Project;

import java.util.List;

//獲取kylin的信息
public interface KylinService {
    ServerResponse<List<Project>> getProjects(String clientKey);
    ServerResponse<String> connect(KylinInfo kylinInfo);
    ServerResponse<List<Cube>> getCubes(String clientKey, String projectName);
    ServerResponse<Cube> getCube(String clientKey, Cube cube);
    ServerResponse<KylinJob> getJob(String clientKey, KylinJob kylinJob);
    ServerResponse<List<KylinJob>> getJobs(String clientKey, List<String> jobids);
}
