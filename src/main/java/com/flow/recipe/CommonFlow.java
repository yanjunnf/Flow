package com.flow.recipe;

import org.apache.log4j.Logger;

import com.flow.common.Status;
import com.flow.step.Step;

/*
 * The flow just like a linked step list.
 * */

public class CommonFlow implements Flow {
    private String name;
    private Step root;
    private Step currentStep;
    private Status status;
    protected Logger logger = Logger.getLogger(CommonFlow.class);
    
    public CommonFlow(String name, Step root) {
        this.name = name;
        this.root = root;
        this.currentStep = root;
        if (root == null)
            status = Status.INITIALIZED;
        else
            status = Status.READY;
    }
    
	@Override
	public Object initialize() {
		return null;
	}
    
    @Override
    public void stop() {
    	synchronized(this){
    		Step step = getCurrentStep();
            if (step != null) {
            	step.stop();
            	setStatus(Status.STOPPED);
            }
    	}
    }
    
    @Override
    public boolean restart() {
    	boolean bRet = false;
    	try {
    		synchronized(this) {
    			if (status == Status.RUNNING) {
    				currentStep.stop();
    				logger.info("Stop the flow");
    			}
    			currentStep = root;
    			bRet = true;
    			logger.info("Restart the flow");
    			start();
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return bRet;
    }
    
    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public Status getStatus() {
        return status;
    }

    public Step getRoot() {
        return root;
    }
    
    @Override
    public void setStatus(Status status) {
        synchronized(this) {
            this.status = status;
        }
    }
    
    @Override
    public Step getCurrentStep() {
        return currentStep;
    }
    
    public void setRoot(Step root) {
        if (status != Status.RUNNING) {
            this.root = root;
            if (root != null)
                setStatus(Status.READY);
            else
                setStatus(Status.INITIALIZED);
        } else
        	logger.warn("The flow is running, cannot change root step");
    }
    
    @Override
    public void start() {
        Status status = getStatus();
        if (status == Status.READY || status == Status.STOPPED) {
            Step start = getRoot();
            if (start != null) {
                setStatus(Status.RUNNING);
                start.execute(null);
            }
        }
    }
}
