package com.delta.report.kylintask.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.delta.report.kylintask.dto.KylinInfo;
import com.delta.report.kylintask.entity.Task;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskDao extends BaseMapper<Task> {
    List<Task> getTasks(KylinInfo kylinInfo);
}
