package com.flow.step;

import java.util.HashMap;
import java.util.Map;
import com.flow.common.Status;
import com.flow.condition.Condition;
import com.flow.process.Flow;

/*
 * This step just like JAVA switch syntax, you can set multiple conditions in this step
 * */
public class SwitchStep extends AbstractStep {
    private Map<Condition, Step> stepMap;
    
    public SwitchStep(String name, Flow flow) {
        super(name, flow);
        stepMap = new HashMap<Condition, Step>();
    }
    
    public void addStep(Condition condition, Step step) {
        stepMap.put(condition, step);
    }
    
    public void removeStep(Condition condition) {
        stepMap.remove(condition);
    }

    @Override
    public Object execute(Object inputData) {
        if (getStatus() == Status.READY)
            return null;

        Object result = null;
        result = super.execute(inputData);
        if (stepMap.size() > 0) {
            for (Condition condition : stepMap.keySet()) {
                if (condition.evaluate(result)) {
                    Step step = stepMap.get(condition);
                    if (step != null)
                        result = stepMap.get(condition).execute(inputData);
                    break;
                }    
            }
        }
        
        return result;
    }

    @Override
    public void stop() {
        this.setStatus(Status.STOPPED);
        for (Step step: stepMap.values()) {
            step.stop();
        }
    }
}
