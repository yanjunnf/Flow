package com.flow.recipe;

import com.flow.common.Status;
import com.flow.step.Step;

public interface Flow {
    public Object start();
    
    public Object restart();
    
    public void stop();
    
    public void setCurrentStep(Step step);
    
    public Status getStatus();
    
    public String getName();
}
