package com.flow.process;

import com.flow.common.Status;
import com.flow.step.Step;

public interface Flow {
    public Object start();
    
    public void stop();
    
    public void setCurrentStep(Step step);
    
    public Status getStatus();
    
    public String getName();
}
