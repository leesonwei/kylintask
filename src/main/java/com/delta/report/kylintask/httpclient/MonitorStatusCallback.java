package com.delta.report.kylintask.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

@Slf4j
public class MonitorStatusCallback extends BaseCallBack {
    @Override
    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
        /*try{
            if (!checkStatusCode(response)){
                return;
            }
            String responseString = response.body().string();
            KylinJob job = JSONObject.parseObject(responseString, KylinJob.class);
            Set<KylinJob> opJobs = (Set<KylinJob>) Kylin.Container.get(Kylin.OP_JOBS);
            Map<String, Integer> resumeJob = (Map<String, Integer>) Kylin.Container.get(Kylin.RESUME_JOB);
            if (job.getJob_status().equals(KylinStatus.FINISHED.toString())) {
                opJobs.remove(job);
                try {
                    resumeJob.remove(job);
                } catch (Exception e) {
                    log.warn("刪除JOB重試計數器異常.可能計數器不存在.");
                }
                log.info(JSON.toJSONString(job));
            } else if (KylinStatus.ERROR.toString().equals(job.getJob_status())) {

                int count;
                try {
                    count = resumeJob.get(job.getUuid());
                } catch (NullPointerException e) {
                    count = 0;
                }
                resumeJob.put(job.getUuid(),  count + 1);
                log.info("檢測到job {}異常. 重試{}次.", job.getUuid(), resumeJob.get(job.getUuid()));
                KylinHttpClient kylinHttpClient = (KylinHttpClient) Kylin.Container.get(Kylin.KYLIN_CLIENT);
                FormBody.Builder builder = new FormBody.Builder();
                RequestBody requestBody = builder.build();
                kylinHttpClient.getResponseSync(KylinURL.KYLIN_RESUME_JOB_URL.toString().replace("{job_uuid}",job.getUuid()),
                        requestBody, RestType.PUT, new ResumeJobCallback());
                Properties properties = (Properties) Kylin.Container.get(Kylin.PROPERTIES);
                if (resumeJob.get(job.getUuid()) == Integer.parseInt(properties.getProperty(KylinPropertiesKeys.KYLIN_JOB_RETRY.toString())) ) {
                    opJobs.remove(job);
                    resumeJob.remove(job);
                    log.info("job {} was try 5 times, suspended.", job.getUuid());
                }
            } else {
                opJobs.remove(job);
                opJobs.add(job);
            }
            log.info("job status:{}.", job.toString());
            //log.debug(responseString);
        } catch (Exception e){
            log.error(e.getMessage() + "{}", e.getCause());
        } finally {
            response.body().close();
        }*/
    }
}
