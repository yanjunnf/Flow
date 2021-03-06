package com.flow.step;

import com.flow.action.Action;
import com.flow.recipe.Flow;
/*
 * The LoopStep just like JAVA while syntax.
 * If the times = -1, this step is infinite loop
 * */
public class LoopStep extends ActionStep {
    private int interval;
    private int times;
    private Step nextStep;
    
    public LoopStep(String name, Action action, Flow flow, Step nextStep, int interval, int times) {
        super(name, action, flow);
        this.interval = interval;
        this.times = times;
        this.nextStep = nextStep;
    }

    @Override
    public Object execute(Object inputData) throws Exception {
        Object result = null;
        result = super.execute(inputData);
        if (times == -1) {
            try {
                while (true) {
                    result = getAction().execute();
                    Thread.sleep(interval);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (times > 0) {
            try {
                while (times-- > 0) {
                    result = getAction().execute();
                    Thread.sleep(interval);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (nextStep != null)
        	result = nextStep.execute(inputData);
        
        return result;
    }
    
    @Override
    public void finish() {
    	//Nothing to do
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
