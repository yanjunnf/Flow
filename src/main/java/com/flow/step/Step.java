package com.flow.step;
/*
 * One step may contain one or more actions.
 * */
public interface Step {        
    public Object execute(Object inputData) throws Exception;
    
    public void interrupt();
    
    /*
     * This method is used to clean resource.
     * It is nothing to do by default.
     * */
    public void finish();
    
    public void reset();
}
