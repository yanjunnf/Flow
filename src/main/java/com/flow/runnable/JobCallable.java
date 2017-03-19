package com.flow.runnable;

import java.util.concurrent.Callable;

import com.flow.job.Job;

public class JobCallable implements Callable<Job> {
    public Job job;
    
    public JobCallable(Job job) {
        this.job = job;
    }
    @Override
    public Job call() throws Exception {
        if (job != null) {
            job.start();
        }
        return job;
    }

}
