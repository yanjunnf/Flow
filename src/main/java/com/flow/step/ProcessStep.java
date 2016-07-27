package com.flow.step;

import com.flow.action.Action;
import com.flow.common.Status;
import com.flow.recipe.Flow;

/*
 * ProcessStep is a step, it is responsible for a single process, no any conditions 
 * */
public class ProcessStep extends ActionStep {
    private Step nextStep;

	public ProcessStep(String name, Action action, Flow flow, Step nextStep) {
        super(name, action, flow);
        this.nextStep = nextStep;
    }

    @Override
    public Object execute(Object inputData) {
        if (getStatus() != Status.READY)
            return null;
        
        Object retValue = null;
        retValue = super.execute(inputData);
        Action action = getAction();
        if (action != null) {
            setStatus(Status.RUNNING);
            Object result = action.execute();
            if (nextStep != null && getStatus() == Status.RUNNING)
                retValue = nextStep.execute(result);
        }
        
        return retValue;
    }

    @Override
    public void stop() {
        setStatus(Status.STOPPED);
        nextStep.stop();
    }
    
    public Step getNextStep() {
		return nextStep;
	}

	public void setNextStep(Step nextStep) {
		this.nextStep = nextStep;
	}
}
