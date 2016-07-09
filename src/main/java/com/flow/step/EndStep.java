package com.flow.step;

/*
 * The end step will do nothing.
 * */
public class EndStep extends AbstractStep implements Step {
	public EndStep(String name) {
		super(name, null);
	}

	@Override
	public Object execute() {
		return null;
	}
}
