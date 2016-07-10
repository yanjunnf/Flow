package com.flow.step;

import com.flow.action.Action;
import com.flow.common.Status;
import com.flow.process.Flow;

/*
 * ProcessStep is a step, it is responsible for a single process, no any conditions 
 * */
public class ProcessStep extends AbstractStep implements Step {
    private Step nextStep;
    
    public ProcessStep(String name, Action action, Flow flow, Step nextStep) {
        super(name, action, flow);
        this.nextStep = nextStep;
    }

    @Override
    public Object execute() {
       Status status = getStatus();
       if (status == Status.READY) {
           Action action = getAction();
           if (action != null) {
               setStatus(Status.RUNNING);
               action.execute();
               if (nextStep != null && getStatus() == Status.RUNNING)
                   nextStep.execute();
           }
       }
        
        return null;
    }

    @Override
    public void stop() {
        setStatus(Status.STOPPED);
    }
}
