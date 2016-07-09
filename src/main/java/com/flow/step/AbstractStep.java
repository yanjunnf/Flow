package com.flow.step;

import com.flow.action.Action;

public abstract class AbstractStep {
	private String name;
	private Action action;
	
	public AbstractStep(String name, Action action) {
		this.name = name;
		this.action = action;
	}
	
	protected boolean evaluate(Object result) {
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
}
