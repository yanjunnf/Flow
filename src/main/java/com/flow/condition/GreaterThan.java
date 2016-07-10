package com.flow.condition;

/*
 * The Object should be [Integer, Float, Double]
 * */
public class GreaterThan implements Condition {
    private Object source;

    public GreaterThan(Object source) {
        this.source = source;
    }
    
    @Override
    public boolean evaluate(Object target) {
        if (source instanceof Integer)
            return ConditionTools.greaterThan((Integer)source, (Integer)target);
        else if (source instanceof Float)
            return ConditionTools.greaterThan((Float)source, (Float)target);
        else if (source instanceof Double)
            return ConditionTools.greaterThan((Double)source, (Double)target);
        return false;
    }
}
