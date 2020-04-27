package com.delta.report.kylintask.httpclient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.delta.report.kylintask.commons.Http;
import com.delta.report.kylintask.dto.CubeDto;
import com.delta.report.kylintask.entity.Cube;
import com.delta.report.kylintask.entity.KylinInfo;
import com.delta.report.kylintask.entity.KylinJob;
import com.delta.report.kylintask.entity.Project;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okhttp3.internal.http2.Header;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Data
@Slf4j
public class SimpleKylinClient implements KylinClient {
    private final static String baseurl="kylin/api";
    private final static String auth="user/authentication";
    private final static String cubes="cubes";
    private final static String rebuild="cubes/{cube_name}/rebuild";
    private final static String build="cubes/{cube_name}/build";
    private final static String streamingbuild="cubes/{cube_name}/build2";
    private final static String streamingrebuild="cubes/{cube_name}/rebuild2";
    private final static String jobs="jobs";
    private final static String projects="projects";
    private final static String resume="jobs/{job_uuid}/resume";

    private KylinInfo kylinInfo;
    private Headers headers;
    private OkHttpClient okHttpClient;

    @Override
    public String connect() throws Exception {
        String url = getUrl(auth);
        RequestBody requestBody = getRequestBody(kylinInfo);
        String requestSync = doRequestSync(url, requestBody, Http.POST, (okhttp3.Callback) null);
        if (requestSync.contains("userDetails")) {
            return String.valueOf(kylinInfo.hashCode());
        } else {
            throw new RuntimeException("can't connect to kylin server");
        }
    }

    @Override
    public List<Project> getProjects() {
        String url = getUrl(projects);
        String project = doRequestSync(url, null, Http.GET, (okhttp3.Callback) null);
        List<Project> projects = JSONObject.parseArray(project, Project.class);
        return projects;
    }

    @Override
    public List<Cube> getCubes(String projectName) {
        String url = getUrl(String.format("%s?projectName=%s&offset=%d&limit=%d", cubes,
                projectName, 0, 200));
        String requestSync =  doRequestSync(url, null, Http.GET, (okhttp3.Callback) null);
        List<Cube> cubes = JSON.parseArray(requestSync, Cube.class);
        return cubes;
    }

    @Override
    public Cube getCube(Cube cube) {
        String url = getUrl(String.format("%s/%s", cubes,cube.getName()));
        String requestSync =  doRequestSync(url, null, Http.GET, (okhttp3.Callback) null);
        List<Cube> cubes = JSON.parseArray(requestSync, Cube.class);
        Optional<Cube> first = cubes.parallelStream().filter(m -> m.getName().equals(cube.getName())).findFirst();
        return first.get();
    }

    @Override
    public void buildCube(CubeDto cube, Callback callback) {
        String url = getUrl(cube.isStreaming() ? streamingbuild : build);
        RequestBody requestBody = getRequestBody(cube);
        doRequestAync(url, requestBody, Http.PUT, new BuildCubeCallback());
    }

    @Override
    public void resumeJob(KylinJob job, Callback callback) {
        String url = getUrl(resume);
        RequestBody requestBody = getRequestBody(job);
        doRequestAync(url, requestBody, Http.PUT, new ResumeJobCallback());
    }

    @Override
    public KylinJob getJob(KylinJob job, Callback callback) {
        String url = getUrl(String.format("%s/%s", jobs, job.getUuid()));
        String requestSync =  doRequestSync(url, null, Http.GET, (okhttp3.Callback) null);
        KylinJob kylinJob = JSON.parseObject(requestSync, KylinJob.class);
        return kylinJob;
    }

    private String getUrl(String detail){
        String url = String.format("%s://%s:%d/%s/%s",kylinInfo.getProtocol(),
                kylinInfo.getHost(), kylinInfo.getPort(), baseurl, detail);
        return url;
    }

    private void doRequestAync(String url, RequestBody requestBody, Http restType, okhttp3.Callback callback){
        Request request = getRequest(url, requestBody, restType);
        okHttpClient.newCall(request).enqueue(callback);
    }

    private Request getRequest(String url, RequestBody requestBody, Http restType){
        Request.Builder builder = new Request.Builder().url(url)
                .headers(initHeader(kylinInfo));
        Request request = null;
        if (restType.equals(Http.PUT)) {
            request = builder.put(requestBody).build();
        } else if (restType.equals(Http.POST)) {
            request = builder.post(requestBody).build();
        } else if (restType.equals(Http.DELETE)) {
            request = builder.delete(requestBody).build();
        } else {
            request = builder.build();
        }
        return request;
    }

    private RequestBody getRequestBody(Object obj){
        RequestBody requestBody = FormBody.create(JSON.toJSONString(obj), MediaType.parse("application/json; charset=utf-8"));
        return requestBody;
    }

    private String doRequestSync(String url, RequestBody requestBody, Http restType, okhttp3.Callback callback){
        Request request = getRequest(url, requestBody, restType);
        Response response = null;
        String responseString = null;
        try {
            Call call = okHttpClient.newCall(request);
            response = call.execute();
            if (callback != null) {
                if (response.isSuccessful()) {
                    callback.onResponse(call, response);
                } else {
                    callback.onFailure(call, new IOException("請求失敗:" + response.message()));
                }
            } else {
                responseString = response.body().string();
                log.debug(responseString);
                return responseString;
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            response.body().close();
        }
        return null;
    }

    private List<Object> doRequestSync(String url, RequestBody requestBody, Http restType, Class result){
        Request request = getRequest(url, requestBody, restType);
        Response response = null;
        String responsBody = null;
        try {
            Call call = okHttpClient.newCall(request);
            response = call.execute();
            responsBody = response.body().string();
            List<Object> object= JSONObject.parseObject(responsBody, (Type) result);
            return object;
        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            response.body().close();
        }
        return null;
    }

    private Headers initHeader(KylinInfo kylinInfo){
        String authBase64 = new String(Base64.getEncoder().encode(String.format("%s:%s", kylinInfo.getUsername(), kylinInfo.getPassword()).getBytes()));
        return new Headers.Builder()
                .add(String.format("Authorization: Basic %s", authBase64))
                .add("Content-Type","application/json;charset=UTF-8")
                .build();
    }
}
