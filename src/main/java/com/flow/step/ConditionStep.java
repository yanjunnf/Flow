package com.flow.step;

import com.flow.action.Action;
import com.flow.condition.Condition;

/*
 * The ConditionStep just like JAVA if-else syntax.
 * leftStep: if the condition returns true, the left step will be triggered.
 * rightStep: if the condition returns false, the right step will be triggered.
 * */
public class ConditionStep extends AbstractStep implements Step {
	private Condition condition;
	private Step leftStep;
	private Step rightStep;
	
	public ConditionStep(String name, Action action, Condition condition, Step leftStep, Step rightStep) {
		super(name, action);
		this.condition = condition;
		this.leftStep = leftStep;
		this.rightStep = rightStep;
	}

	@Override
	protected boolean evaluate(Object result) {
		if (condition != null)
			return condition.evaluate(result);
		return false;
	}

	@Override
	public Object execute() {
		Object retValue = null;
		Action action = getAction();
		if (action != null) {
			Object object = action.execute();
			if (evaluate(object))
				retValue = leftStep.execute();
			else
				retValue = rightStep.execute();
		}
		return retValue;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Step getLeftStep() {
		return leftStep;
	}

	public void setLeftStep(Step leftStep) {
		this.leftStep = leftStep;
	}

	public Step getRightStep() {
		return rightStep;
	}

	public void setRightStep(Step rightStep) {
		this.rightStep = rightStep;
	}
}
