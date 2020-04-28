package com.delta.report.kylintask.controller;

import com.delta.report.kylintask.commons.ServerResponse;
import com.delta.report.kylintask.entity.Cube;
import com.delta.report.kylintask.dto.KylinInfo;
import com.delta.report.kylintask.entity.KylinJob;
import com.delta.report.kylintask.entity.Project;
import com.delta.report.kylintask.service.KylinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("kylin")
public class KylinController {
    @Autowired
    private KylinService kylinService;

    @PostMapping("/connect")
    public ServerResponse<String> connect(KylinInfo kylinInfo){
        return kylinService.connect(kylinInfo);
    }

    @GetMapping("/projects")
    public ServerResponse<List<Project>> projects(String clientKey){
        return kylinService.getProjects(clientKey);
    }

    @GetMapping("/cubes")
    public ServerResponse<List<Cube>> cubes(String clientKey, String projectName){
        return kylinService.getCubes(clientKey, projectName);
    }

    @GetMapping("/cubes/{cube_name}")
    public ServerResponse<Cube> cube(String clientKey, @PathVariable("cube_name") String cubeName){
        Cube cube = new Cube();
        cube.setName(cubeName);
        return kylinService.getCube(clientKey, cube);
    }

    @GetMapping("/jobs/{job_uuid}")
    public ServerResponse<KylinJob> job(String clientKey, @PathVariable("job_uuid") String jobUuid){
        KylinJob kylinJob = new KylinJob();
        kylinJob.setUuid(jobUuid);
        return kylinService.getJob(clientKey, kylinJob);
    }

    @GetMapping("/jobs")
    public ServerResponse<List<KylinJob>> jobs(String clientKey, List<String> jobids){
        return kylinService.getJobs(clientKey, jobids);
    }

}
