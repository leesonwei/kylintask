package com.delta.report.kylintask.entity;

import lombok.Data;

@Data
public class Realization {
    private Type type;
    private String realization;
}
enum Type {
    /**
     * cube
     */
    CUBE("CUBE");
    private String name;
    Type(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
