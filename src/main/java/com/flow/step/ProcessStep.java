package com.flow.step;

import com.flow.action.Action;
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
    public Object execute(Object inputData) throws Exception {
        Object retValue = null;
        retValue = super.execute(inputData);
        Action action = getAction();
        if (action != null) {
            try {
            	Object result = action.execute();
                if (nextStep != null)
                    retValue = nextStep.execute(result);
            } catch (Exception e) {
				throw e;
            }
        }
        
        return retValue;
    }

    @Override
    public void interrupt() {
        nextStep.interrupt();
    }
    
    public Step getNextStep() {
		return nextStep;
	}
    
    @Override
    public void finish(){
    	//Nothing to do
    }

	public void setNextStep(Step nextStep) {
		this.nextStep = nextStep;
	}
}
