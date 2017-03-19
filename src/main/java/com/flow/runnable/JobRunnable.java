package com.flow.runnable;

import com.flow.job.Job;

public class JobRunnable implements Runnable {
    private Job job;
    
    public JobRunnable(Job job) {
        this.job = job;
    }
    @Override
    public void run() {
        if (job != null)
            job.start();
    }

}
