package com.flow.step;

import java.util.logging.Logger;
import com.flow.condition.Condition;
import com.flow.recipe.Flow;

/*
 * The ConditionStep just like JAVA if-else syntax.
 * condition: the result of parent step will be evaluated by condition object.
 * leftStep: if the condition returns true, the left step will be triggered.
 * rightStep: if the condition returns false, the right step will be triggered.
 * */
public class ConditionStep extends AbstractStep {
    private Condition condition;
    private Step leftStep;
    private Step rightStep;
    private static Logger logger = Logger.getLogger(ConditionStep.class.toString());
    
    public ConditionStep(String  name, Flow flow, Condition condition, Step leftStep, Step rightStep) {
        super(name, flow);
        this.condition = condition;
        this.leftStep = leftStep;
        this.rightStep = rightStep;
    }

    @Override
    protected boolean evaluate(Object result) {
        if (condition != null)
            return condition.evaluate(result);
        logger.warning("The condition is null. Step name=" + getName());
        return false;
    }

    @Override
    public Object execute(Object inputData) throws Exception {
        Object retValue = null;
        if (evaluate(inputData)) {
            logger.info("Evaluated the result successfully. Go to left step");
            retValue = leftStep.execute(inputData);
        }
        else {
            logger.info("Failed to evaluate the result. Go to right step");
            retValue = rightStep.execute(inputData);
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
    
    @Override
    public void interrupt() {
        super.interrupt();
        leftStep.interrupt();
        rightStep.interrupt();
    }
    
    @Override
    public void finish() {
    	//Nothing to do
    }
}
