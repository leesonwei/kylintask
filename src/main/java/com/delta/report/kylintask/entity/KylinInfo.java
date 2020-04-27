package com.delta.report.kylintask.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

@Data
@EqualsAndHashCode(exclude = "password")
@Slf4j
public class KylinInfo {
    private String protocol;
    private String host;
    private Integer port;
    private String username;
    private String password;
}
