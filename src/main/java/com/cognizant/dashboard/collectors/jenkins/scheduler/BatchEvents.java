package com.cognizant.dashboard.collectors.jenkins.scheduler;

/**
 * Created by 784420 on 11/20/2019 12:37 PM
 */
public interface BatchEvents {
    void beforeJob();
    void beforeTask();
    void afterTask();
    void afterJob();
}
