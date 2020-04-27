package com.delta.report.kylintask.commons;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeltaResponse<T> {
    private Integer code;
    private String msg;
    private T data;
}
