package com.flow.condition;

public class RegexCondition implements Condition {
	private String regex;
	
	public RegexCondition(String regex) {
		this.regex = regex;
	}
	
	@Override
	public boolean evaluate(Object target) {
		return ((String)target).matches(regex);
	}
}
