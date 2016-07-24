package com.flow.step;

import org.apache.log4j.Logger;
import com.flow.common.Status;
import com.flow.process.Flow;

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
    private Status status;
    private Object inputData;
    protected Logger logger = Logger.getLogger(AbstractStep.class);
    
    public AbstractStep(String name, Flow flow) {
        this.name = name;
        this.flow = flow;
        status = Status.READY;
        inputData = null;
    }
    
    @Override
    public Object execute(Object inputData) {
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

    public Status getStatus() {
        synchronized(this) {
            return status;
        }
    }

    public void setStatus(Status status) {
        synchronized(this) {
            this.status = status;
        }
    }

    public Object getInputData() {
        return inputData;
    }

    public void setInputData(Object inputData) {
        this.inputData = inputData;
    }
    
    @Override
    public void stop() {
        logger.info("This step stopped.");
        setStatus(Status.STOPPED);
    }
}
