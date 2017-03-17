package com.flow.recipe;

import com.flow.common.Status;
import com.flow.step.Step;

public interface Flow {
	public Object initialize();
	
    public void start();
    
    public boolean restart();
    
    public void stop();
    
    public Step getCurrentStep();
    
    public void setStatus(Status status);
    
    public Status getStatus();
    
    public void setName(String name);
    
    public String getName();
}
