package com.flow.condition;

public class RegexCondition implements Condition {
	private String regex;
	private String target;
	
	public RegexCondition(String regex, String target) {
		this.regex = regex;
		this.target = target;
	}
	
	@Override
	public boolean evaluate() {
		return target.matches(regex);
	}
}
