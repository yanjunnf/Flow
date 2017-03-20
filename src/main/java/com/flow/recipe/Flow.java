package com.flow.recipe;

import com.flow.common.Status;
import com.flow.step.Step;

public interface Flow {
	public Object initialize();
	
    public void start();
    
    public boolean restart();
    
    public void interrupt();
    
    public Step getCurrentStep();
    
    public void setStatus(Status status);
    
    public Status getStatus();
    
    public void setName(String name);
    
    public String getName();
    
    public void reset();
}
