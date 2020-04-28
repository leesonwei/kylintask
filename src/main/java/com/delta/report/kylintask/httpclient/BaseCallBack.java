package com.delta.report.kylintask.httpclient;

import com.alibaba.fastjson.JSONObject;
import com.delta.report.kylintask.dto.KylinError;
import com.delta.report.kylintask.exception.KylinException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

@Data
@Slf4j
public class BaseCallBack implements Callback {
    private String clientKey;

    @Override
    public void onFailure(@NotNull Call call, @NotNull IOException e) {
        log.error(e.getMessage() + ". {}", e.getCause());
    }

    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        if (!response.isSuccessful()) {
            KylinError kylinError = JSONObject.parseObject(response.body().string(), KylinError.class);
            log.error(kylinError.toString());
            throw new KylinException(kylinError);
        }
    }
}
