package com.flow.step;

import com.flow.action.Action;
import com.flow.process.Flow;
/*
 * The LoopStep just like JAVA while syntax.
 * If the times = -1, this step is infinite loop
 * */
public class LoopStep extends AbstractStep implements Step{
    private int interval;
    private int times;
    
    public LoopStep(String name, Action action, Flow flow, int interval, int times) {
        super(name, action, flow);
        this.interval = interval;
        this.times = times;
    }

    @Override
    public Object execute() {
        Object result = null;
        if (times == -1) {
            try {
                while (true) {
                    result = getAction().execute();
                    Thread.sleep(interval);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (times > 0){
            try {
                while (times-- > 0) {
                    result = getAction().execute();
                    Thread.sleep(interval);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    @Override
    public void stop() {
    }

}
