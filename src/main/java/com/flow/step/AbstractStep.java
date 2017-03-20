package com.flow.step;

import java.util.logging.Logger;
import com.flow.recipe.Flow;

/*
 * Step has 4 status:
 * INITIALIZED:
 * READY:
 * RUNNING:
 * STOPPING:
 * STOPPED:
 * inputData: the data from the parent step
 * */
public abstract class AbstractStep implements Step {
    private String name;
    private Flow flow;
    private Object inputData;
    protected Logger logger = Logger.getLogger(AbstractStep.class.toString());
    
    public AbstractStep(String name, Flow flow) {
        this.name = name;
        this.flow = flow;
        inputData = null;
    }
    
    @Override
    public Object execute(Object inputData) throws Exception {
        this.inputData = inputData;
        return null;
    }
    
    protected boolean evaluate(Object result) {
        return false;
    }
    
    public void setCurrentStep() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Flow getFlow() {
        return flow;
    }

    public void setFlow(Flow flow) {
        this.flow = flow;
    }

    public Object getInputData() {
        return inputData;
    }

    public void setInputData(Object inputData) {
        this.inputData = inputData;
    }
    
    @Override
    public void interrupt() {
        logger.info("This step interrupted.");
    }
    
    @Override
    public void reset() {
    }
}
