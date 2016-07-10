package com.flow.process;

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
        Status status = getStatus();
        if (status == Status.RUNNING) {
            Step currentStep = getCurrentStep();
            if (currentStep != null) {
                currentStep.stop();
                setStatus(Status.STOPPED);
                currentStep = null;
            }
        }
    }
}
 