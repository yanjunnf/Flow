package com.flow.step;

import java.util.HashMap;
import java.util.Map;

import com.flow.action.Action;
import com.flow.condition.Condition;
import com.flow.process.Flow;

/*
 * This step just like JAVA switch syntax, you can set multiple conditions in this step
 * */
public class SwitchStep extends AbstractStep {
    private Map<Condition, Step> stepMap;
    
    public SwitchStep(String name, Action action, Flow flow) {
        super(name, action, flow);
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
        super.execute(inputData);
        Action action = getAction();
        Object result = null;
        if (action != null && stepMap.size() > 0) {
            result = action.execute();
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
    }
}
