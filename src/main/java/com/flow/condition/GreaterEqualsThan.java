package com.flow.condition;

public class GreaterEqualsThan<T> implements Condition {
	private T source;
	private T target;
	
	public GreaterEqualsThan(T source, T target) {
		this.source = source;
		this.target = target;
	}
	
	@Override
	public boolean evaluate() {
		if (source instanceof Integer)
			return ConditionTools.greaterThan((Integer)source, (Integer)target);
		else if (source instanceof Float)
			return ConditionTools.greaterThan((Float)source, (Float)target);
		else if (source instanceof Double)
			return ConditionTools.greaterThan((Double)source, (Double)target);
		return false;
	}

}
