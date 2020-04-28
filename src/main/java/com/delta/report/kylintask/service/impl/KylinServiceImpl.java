package com.delta.report.kylintask.service.impl;

import com.delta.report.kylintask.commons.ServerResponse;
import com.delta.report.kylintask.entity.Cube;
import com.delta.report.kylintask.dto.KylinInfo;
import com.delta.report.kylintask.entity.KylinJob;
import com.delta.report.kylintask.entity.Project;
import com.delta.report.kylintask.httpclient.HttpClientFactory;
import com.delta.report.kylintask.httpclient.KylinClient;
import com.delta.report.kylintask.service.KylinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class KylinServiceImpl implements KylinService {
    @Autowired
    private HttpClientFactory httpClientFactory;

    @Override
    public ServerResponse<List<Project>> getProjects(String clientKey) {
        KylinClient client = httpClientFactory.getKylinClient(clientKey);
        try {
            List<Project> projects = client.getProjects();
            return ServerResponse.createBySuccess(projects);
        } catch (Exception e) {
            log.error(e.getMessage() + ", {}", e.getCause());
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
    }

    @Override
    public ServerResponse<String> connect(KylinInfo kylinInfo) {
        KylinClient client = httpClientFactory.getKylinClient(kylinInfo);
        try {
            String key = client.connect();
            return ServerResponse.createBySuccess(key);
        } catch (Exception e) {
            log.error(e.getMessage() + ", {}", e.getCause());
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
    }

    @Override
    public ServerResponse<List<Cube>> getCubes(String clientKey, String projectName) {
        KylinClient client = httpClientFactory.getKylinClient(clientKey);
        return ServerResponse.createBySuccess(client.getCubes(projectName));
    }

    @Override
    public ServerResponse<Cube> getCube(String clientKey, Cube cube) {
        KylinClient client = httpClientFactory.getKylinClient(clientKey);
        return ServerResponse.createBySuccess(client.getCube(cube));
    }

    @Override
    public ServerResponse<KylinJob> getJob(String clientKey, KylinJob kylinJob) {
        KylinClient client = httpClientFactory.getKylinClient(clientKey);
        return ServerResponse.createBySuccess(client.getJob(kylinJob, null));
    }

    @Override
    public ServerResponse<List<KylinJob>> getJobs(String clientKey, List<String> jobids) {
        List<KylinJob> kylinJobs = new ArrayList<>();
        KylinJob jobDto = new KylinJob();
        for (String jobid : jobids) {
            jobDto.setUuid(jobid);
            ServerResponse<KylinJob> result= getJob(clientKey, jobDto);
            kylinJobs.add(result.getData());
        }
        return ServerResponse.createBySuccess(kylinJobs);
    }
}
