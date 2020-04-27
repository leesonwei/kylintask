package com.delta.report.kylintask.httpclient;


import ch.qos.logback.core.net.server.Client;
import com.delta.report.kylintask.entity.KylinInfo;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Component
public class HttpClientFactory {
    private Map<Integer, KylinClient> clientPool = new HashMap<>();

    public KylinClient getKylinClient(KylinInfo kylinInfo){
        SimpleKylinClient simpleKylinClient = (SimpleKylinClient) clientPool.get(kylinInfo.hashCode());
        if (null == simpleKylinClient) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .callTimeout(25, TimeUnit.SECONDS)
                    .connectTimeout(25, TimeUnit.SECONDS)
                    .build();
            simpleKylinClient = new SimpleKylinClient();
            simpleKylinClient.setKylinInfo(kylinInfo);
            simpleKylinClient.setOkHttpClient(okHttpClient);
            clientPool.put(kylinInfo.hashCode(),simpleKylinClient);
        }
        return simpleKylinClient;
    }

    public KylinClient getKylinClient(String clientKey){
        return clientPool.get(Integer.parseInt(clientKey));
    }
}
