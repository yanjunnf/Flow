package com.flow.action;

public abstract class AbstractAction implements Action{
    private String name;
    private boolean asyncAction;
    
    public AbstractAction(String name, boolean asyncAction) {
        this.name = name;
        this.asyncAction = asyncAction;
    }
    
    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAsyncAction() {
        return asyncAction;
    }

    public void setAsyncAction(boolean asyncAction) {
        this.asyncAction = asyncAction;
    }
    
    public void stop() {
    }
}
