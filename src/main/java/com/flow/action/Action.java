package com.flow.action;

public interface Action {    
    public Object execute();
    
    public String getName();
    
    public void stop();
}
