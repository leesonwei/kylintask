package com.delta.report.kylintask.httpclient;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Slf4j
public class GetCubeCallback extends BaseCallBack {
    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        /*try{
            if (!checkStatusCode(response)){
                return;
            }
            String responseString = response.body().string();
            List<Cube> cubes = JSON.parseArray(responseString, Cube.class);
            Set<Cube> webCubePool = (Set<Cube>) Kylin.Container.get(Kylin.WEB_CUBE_POOL);
            webCubePool.addAll(cubes);
            log.debug(responseString);
        } catch (Exception e){
            log.error(e.getMessage() + "{}", e.getCause());
        } finally {
            response.body().close();
        }*/
    }
}
