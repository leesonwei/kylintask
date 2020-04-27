package com.delta.report.kylintask.controller;

import com.delta.report.kylintask.commons.DeltaResponse;
import com.delta.report.kylintask.dto.CubeDto;
import com.delta.report.kylintask.entity.Cube;
import com.delta.report.kylintask.entity.KylinInfo;
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
    public DeltaResponse<String> connect(KylinInfo kylinInfo){
        return kylinService.connect(kylinInfo);
    }

    @GetMapping("/projects")
    public DeltaResponse<List<Project>> projects(String clientKey){
        return kylinService.getProjects(clientKey);
    }

    @GetMapping("/cubes")
    public DeltaResponse<List<Cube>> cubes(String clientKey, String projectName){
        return kylinService.getCubes(clientKey, projectName);
    }

    @GetMapping("/cubes/{cube_name}")
    public DeltaResponse<Cube> cube(String clientKey, @PathVariable String cube_name){
        Cube cube = new Cube();
        cube.setName(cube_name);
        return kylinService.getCube(clientKey, cube);
    }

    @GetMapping("/jobs/{job_uuid}")
    public DeltaResponse<KylinJob> job(String clientKey, @PathVariable String job_uuid){
        KylinJob kylinJob = new KylinJob();
        kylinJob.setName(job_uuid);
        return kylinService.getJob(clientKey, kylinJob);
    }

}
