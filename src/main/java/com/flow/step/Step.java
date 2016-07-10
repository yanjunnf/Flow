package com.flow.step;
/*
 * One step may contain one or more actions.
 * */
public interface Step {    
    public Object execute();
    
    public void stop();
}
