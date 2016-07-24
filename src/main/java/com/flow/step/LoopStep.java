package com.flow.step;

import com.flow.action.Action;
import com.flow.common.Status;
import com.flow.process.Flow;
/*
 * The LoopStep just like JAVA while syntax.
 * If the times = -1, this step is infinite loop
 * */
public class LoopStep extends ActionStep {
    private int interval;
    private int times;
    
    public LoopStep(String name, Action action, Flow flow, int interval, int times) {
        super(name, action, flow);
        this.interval = interval;
        this.times = times;
    }

    @Override
    public Object execute(Object inputData) {
        if (getStatus() != Status.READY)
            return null;;

        Object result = null;
        result = super.execute(inputData);
        if (times == -1) {
            try {
                while (getStatus() == Status.READY) {
                    result = getAction().execute();
                    Thread.sleep(interval);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (times > 0){
            try {
                while (times-- > 0 && getStatus() == Status.READY) {
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
}
