package com.delta.report.kylintask.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.delta.report.kylintask.entity.Cube;
import com.delta.report.kylintask.entity.KylinJob;
import com.delta.report.kylintask.entity.Task;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@Data
@Slf4j
public class BuildCubeCallback extends BaseCallBack {
    private Task task;

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        try{
            super.onResponse(call, response);
            String responseString = response.body().string();
            KylinJob job = JSONObject.parseObject(responseString, KylinJob.class);
            //todo 將job插入數據庫

            log.info("build cubuccessed. job info {}", JSON.toJSONString(job));
            log.debug(responseString);
        } catch (Exception e){
            log.error(e.getMessage() + "{}", e.getStackTrace());
        } finally {
            response.body().close();
        }
    }
}
