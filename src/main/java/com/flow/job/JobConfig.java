package com.flow.job;

public class JobConfig {
    private long delay = 0;
    private long period = 0;
    
    public JobConfig (int delay, int period) {
        this.delay = delay;
        this.period = period;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay(long delay) {
        this.delay = delay;
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }
}
