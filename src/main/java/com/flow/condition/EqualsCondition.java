package com.flow.condition;

public class EqualsCondition<T> implements Condition {
	private T source;
	private T target;
	
	public EqualsCondition(T source, T target) {
		this.source = source;
		this.target = target;
	}
	
	@Override
	public boolean evaluate() {
		return source.equals(target) ;
	}
}
