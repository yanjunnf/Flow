package com.flow.action;

import java.util.logging.Logger;

/*
 * Wait Action is used to delay a specified time.
 * Unit: millisecond
 * */
public class WaitAction extends AbstractAction{
    private int sleep;
    private Logger logger = Logger.getLogger(ReadFileAction.class.toString());
    
    public WaitAction(String name, int sleep) {
        super(name, false);
    }

    @Override
    public Object execute() throws Exception{
        try {
            Thread.sleep(sleep);
        } catch (Exception e) {
            logger.severe("Failed to run WaitAction. Message=" + e.getMessage());
            throw e;
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
