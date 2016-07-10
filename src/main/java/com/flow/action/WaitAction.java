package com.flow.action;

public class WaitAction extends AbstractAction implements Action{
    private int sleep;
    
    public WaitAction(String name, boolean asyncAction, int sleep) {
        super(name, asyncAction);
    }

    @Override
    public Object execute() {
        try {
            Thread.sleep(sleep);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getSleep() {
        return sleep;
    }

    public void setSleep(int sleep) {
        this.sleep = sleep;
    }

}
