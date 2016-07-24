package com.flow.process;

import com.flow.common.Status;
import com.flow.step.Step;

/*
 * The flow just like a linked step list.
 * */

public abstract class AbstractFlow implements Flow {
    private String name;
    private Step start;
    private Step currentStep;
    private Status status;
    
    public AbstractFlow(String name, Step start) {
        this.name = name;
        this.start = start;
        currentStep = null;
        if (start == null)
            status = Status.INITIALIZED;
        else
            status = Status.READY;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Step getStart() {
        return start;
    }

    public void setStart(Step start) {
        if (status != Status.RUNNING) {
            this.start = start;
            if (start != null)
                setStatus(Status.READY);
            else
                setStatus(Status.INITIALIZED);
        }
    }
    
    public void removeStep(Step step) {
    }
    
    public void appendStep(Step parentStep, Step newStep) {
    }

    public Step getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(Step currentStep) {
        synchronized(this) {
            this.currentStep = currentStep;
        }
    }

    public Status getStatus() {
            return status;
    }

    public void setStatus(Status status) {
        synchronized(this) {
            this.status = status;
        }
    }
}
