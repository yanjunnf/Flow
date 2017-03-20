package com.flow.step;

import com.flow.action.Action;
import com.flow.recipe.Flow;

public abstract class ActionStep extends AbstractStep {
    private Action action;
    
    public ActionStep(String name, Action action, Flow flow) {
        super(name, flow);
        this.action = action;    }
    
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}
