package com.flow.step;

import com.flow.action.Action;
import com.flow.common.Status;
import com.flow.process.Flow;

public abstract class AbstractStep {
    private String name;
    private Action action;
    private Flow flow;
    private Status status;
    
    public AbstractStep(String name, Action action, Flow flow) {
        this.name = name;
        this.action = action;
        this.flow = flow;
        if (action != null)
            status = Status.READY;
        else
            status = Status.INITIALIZED;
    }
    
    protected boolean evaluate(Object result) {
        return false;
    }
    
    public void setCurrentStep() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    public Status getStatus() {
        synchronized(this) {
            return status;
        }
    }

    public void setStatus(Status status) {
        synchronized(this) {
            this.status = status;
        }
    }
}
