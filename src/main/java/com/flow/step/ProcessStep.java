package com.flow.step;

import com.flow.action.Action;

/*
 * ProcessStep is a step, it is responsible for a single process, no any conditions 
 * */
public class ProcessStep extends AbstractStep implements Step {

	public ProcessStep(String name, Action action) {
		super(name, action);
	}

	@Override
	public Object execute() {
		Action action = getAction();
		if (action != null)
			return action.execute();
		return null;
	}
}
