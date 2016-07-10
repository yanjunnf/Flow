package com.flow.condition;

public class EqualsCondition implements Condition {
    private Object source;
    
    public EqualsCondition(Object source) {
        this.source = source;
    }
    
    @Override
    public boolean evaluate(Object target) {
        return source.equals(target) ;
    }
}
