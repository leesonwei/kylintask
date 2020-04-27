package com.delta.report.kylintask.commons;

public enum KylinActions {
    /**
     *
     */
    REBUILD("REBUILD"), BUILD("BUILD"), REFRESH("REFRESH"), MERGE("MERGE");
    private String name;
    KylinActions(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
}
