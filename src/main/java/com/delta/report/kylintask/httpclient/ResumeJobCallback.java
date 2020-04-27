package com.delta.report.kylintask.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@Slf4j
public class ResumeJobCallback extends BaseCallBack {
    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        try{
            super.onResponse(call, response);
            String responseString = response.body().string();
            JSONObject jsonObject = JSON.parseObject(responseString);
            log.info(responseString);
        } catch (Exception e){
            log.error(e.getMessage() + "{}", e.getCause());
        } finally {
            response.body().close();
        }
    }
}
