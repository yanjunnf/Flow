package com.flow.condition;

public class ContainCondition implements Condition{
    private String source;
    
    public ContainCondition(String source) {
        this.source = source;
    }
    
    @Override
    public boolean evaluate(Object target) {
        return source.contains((String)target);
    }
}
