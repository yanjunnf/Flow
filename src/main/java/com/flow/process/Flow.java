package com.flow.process;

import com.flow.step.Step;

public interface Flow {
    public Object start();
    
    public void stop();
    
    public void setCurrentStep(Step step);
}
