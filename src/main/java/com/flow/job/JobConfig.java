package com.flow.job;

public class JobConfig {
    private long delay = 0;
    private long interval = 0;
    
    public JobConfig (int delay, int interval) {
        this.delay = delay;
        this.interval = interval;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }
}
