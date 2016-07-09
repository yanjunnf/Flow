package com.flow.action;

import java.util.List;

/*
 * One step may contain one or more actions.
 * */
public interface Step {
	public String name();
	
	public boolean evaluate();
	
	public Object execute();
	
	public List<Action> actions();
}
