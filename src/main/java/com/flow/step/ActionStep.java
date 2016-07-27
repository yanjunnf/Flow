package com.flow.step;

import com.flow.action.Action;
import com.flow.common.Status;
import com.flow.recipe.Flow;

public abstract class ActionStep extends AbstractStep {
    private Action action;
    
    public ActionStep(String name, Action action, Flow flow) {
        super(name, flow);
        this.action = action;
        
        if (action != null)
            setStatus(Status.READY);
        else
            setStatus(Status.INITIALIZED);
    }
    
    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        Status status = getStatus();
        if (status != Status.RUNNING) {
            this.action = action;
            if (action != null)
                setStatus(Status.READY);
            else
                setStatus(Status.INITIALIZED);
        }
    }

}
