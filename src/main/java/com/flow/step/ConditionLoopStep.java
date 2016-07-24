package com.flow.step;

import com.flow.action.Action;
import com.flow.common.Status;
import com.flow.condition.Condition;
import com.flow.process.Flow;

public class ConditionLoopStep extends LoopStep {
    private Condition condition;
    
    public ConditionLoopStep(String name, Action action, Flow flow, int interval, int times, Condition condition) {
        super(name, action, flow, interval, times);
        this.condition = condition;
    }
    
    @Override
    public Object execute(Object inputData) {
        if (getStatus() != Status.READY)
            return null;
        Object result = null;
        if (condition == null)
            return super.execute(inputData);
        
        result = super.execute(inputData);
        int times = getTimes();
        int interval = getInterval();
        if (times == -1) {
            try {
                while (getStatus() == Status.READY) {
                    result = getAction().execute();
                    if (!condition.evaluate(result))
                        break;
                    Thread.sleep(interval);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (times > 0) {
            try {
                while (times-- > 0 && getStatus() == Status.READY) {
                    result = getAction().execute();
                    if (!condition.evaluate(result))
                        break;
                    Thread.sleep(interval);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}
