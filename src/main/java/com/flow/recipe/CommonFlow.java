package com.flow.recipe;

import com.flow.common.Status;
import com.flow.step.Step;

public class CommonFlow extends AbstractFlow {
    public CommonFlow(String name, Step start) {
        super(name, start);
    }

    @Override
    public Object start() {
        Status status = getStatus();
        if (status == Status.READY || status == Status.STOPPED) {
            Step start = getStart();
            if (start != null) {
                setStatus(Status.RUNNING);
                return start.execute(null);
            }
        }

        return null;
    }

    @Override
    public void stop() {
        Step step = this.getStart();
        step.stop();
    }
    
    @Override
    public Object restart() {
    	Step start = getStart();
    	if (start != null) {
    		start.stop();
    		logger.info("Flow stopped.");
    	}
    	start.reset();
    	logger.info("Flow is executing...");
    	return start.execute(null);
    }
}
 