package com.delta.report.kylintask.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

public class KylinJobListener extends JobListenerSupport {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {

    }
}
