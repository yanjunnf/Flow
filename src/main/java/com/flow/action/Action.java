package com.flow.action;

/*
 * The action is the smallest unit in flow.
 * */
public interface Action {    
    public Object execute() throws Exception;
    
    public String getName();
}
