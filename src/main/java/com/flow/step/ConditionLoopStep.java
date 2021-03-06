package com.flow.step;

import com.flow.action.Action;
import com.flow.condition.Condition;
import com.flow.recipe.Flow;

public class ConditionLoopStep extends LoopStep {
    private Condition condition;
    
    public ConditionLoopStep(String name, Action action, Flow flow, Step nextStep, int interval, int times, Condition condition) {
        super(name, action, flow, nextStep, interval, times);
        this.condition = condition;
    }
    
    @Override
    public Object execute(Object inputData) throws Exception {
        setInputData(inputData);
        if (condition == null) {
        	logger.warning("The condition is empty");
            return super.execute(inputData);
        }
        
        Object result = null;
        int times = getTimes();
        int interval = getInterval();
        if (times == -1) {
            try {
                while (true) {
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
                while (times-- > 0) {
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
