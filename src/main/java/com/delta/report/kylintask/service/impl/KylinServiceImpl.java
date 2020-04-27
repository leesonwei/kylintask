package com.delta.report.kylintask.service.impl;

import com.delta.report.kylintask.commons.DeltaResponse;
import com.delta.report.kylintask.entity.Cube;
import com.delta.report.kylintask.entity.KylinInfo;
import com.delta.report.kylintask.entity.KylinJob;
import com.delta.report.kylintask.entity.Project;
import com.delta.report.kylintask.httpclient.HttpClientFactory;
import com.delta.report.kylintask.httpclient.KylinClient;
import com.delta.report.kylintask.httpclient.SimpleKylinClient;
import com.delta.report.kylintask.service.KylinService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class KylinServiceImpl implements KylinService {
    @Autowired
    private HttpClientFactory httpClientFactory;
    @Override
    public DeltaResponse<List<Project>> getProjects(String clientKey) {
        KylinClient client = httpClientFactory.getKylinClient(clientKey);
        DeltaResponse<List<Project>> deltaResponse = new DeltaResponse<>();
        deltaResponse.setCode(0);
        deltaResponse.setMsg("successed");
        try {
            List<Project> projects = client.getProjects();
            deltaResponse.setData(projects);
        } catch (Exception e) {
            log.error(e.getMessage() + ", {}", e.getCause());
            deltaResponse.setCode(1);
            deltaResponse.setMsg(e.getMessage());
        }
        return deltaResponse;
    }

    @Override
    public DeltaResponse<String> connect(KylinInfo kylinInfo) {
        KylinClient client = httpClientFactory.getKylinClient(kylinInfo);
        DeltaResponse<String> deltaResponse = new DeltaResponse<>();
        deltaResponse.setCode(0);
        deltaResponse.setMsg("successed");
        try {
            String key = client.connect();
            deltaResponse.setData(key);
        } catch (Exception e) {
            log.error(e.getMessage() + ", {}", e.getCause());
            deltaResponse.setCode(99);
            deltaResponse.setMsg(e.getMessage());
        }
        return deltaResponse;
    }

    @Override
    public DeltaResponse<List<Cube>> getCubes(String clientKey, String projectName) {
        KylinClient client = httpClientFactory.getKylinClient(clientKey);
        DeltaResponse<List<Cube>> deltaResponse = new DeltaResponse<>();
        deltaResponse.setCode(0);
        deltaResponse.setMsg("successed");
        deltaResponse.setData(client.getCubes(projectName));
        return deltaResponse;
    }

    @Override
    public DeltaResponse<Cube> getCube(String clientKey, Cube cube) {
        KylinClient client = httpClientFactory.getKylinClient(clientKey);
        DeltaResponse<Cube> deltaResponse = new DeltaResponse<>();
        deltaResponse.setCode(0);
        deltaResponse.setMsg("successed");
        deltaResponse.setData(client.getCube(cube));
        return deltaResponse;
    }

    @Override
    public DeltaResponse<KylinJob> getJob(String clientKey, KylinJob kylinJob) {
        KylinClient client = httpClientFactory.getKylinClient(clientKey);
        DeltaResponse<KylinJob> deltaResponse = new DeltaResponse<>();
        deltaResponse.setCode(0);
        deltaResponse.setMsg("successed");
        deltaResponse.setData(client.getJob(kylinJob, null));
        return deltaResponse;
    }
}
