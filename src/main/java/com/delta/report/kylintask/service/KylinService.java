package com.delta.report.kylintask.service;

import com.delta.report.kylintask.commons.DeltaResponse;
import com.delta.report.kylintask.dto.CubeDto;
import com.delta.report.kylintask.entity.Cube;
import com.delta.report.kylintask.entity.KylinInfo;
import com.delta.report.kylintask.entity.KylinJob;
import com.delta.report.kylintask.entity.Project;

import java.util.List;

public interface KylinService {
    DeltaResponse<List<Project>> getProjects(String clientKey);
    DeltaResponse<String> connect(KylinInfo kylinInfo);
    DeltaResponse<List<Cube>> getCubes(String clientKey, String projectName);
    DeltaResponse<Cube> getCube(String clientKey, Cube cube);
    DeltaResponse<KylinJob> getJob(String clientKey, KylinJob kylinJob);
}
