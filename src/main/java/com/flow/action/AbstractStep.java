package com.flow.action;

public abstract class AbstractStep {
	private String name;
	private Action action;
	
	public AbstractStep(String name, Action action) {
		this.name = name;
		this.action = action;
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
