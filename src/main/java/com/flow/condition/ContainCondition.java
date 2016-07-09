package com.flow.condition;

public class ContainCondition implements Condition{
	private String source;
	private String target;
	
	public ContainCondition(String source, String target) {
		this.source = source;
		this.target = target;
	}
	
	@Override
	public boolean evaluate() {
		return source.contains(target);
	}
	
	
}
