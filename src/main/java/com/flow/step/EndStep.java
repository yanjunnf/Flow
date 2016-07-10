package com.flow.step;

import com.flow.process.Flow;

/*
 * The end step will do nothing.
 * */
public class EndStep extends AbstractStep implements Step {
    public EndStep(String name, Flow flow) {
        super(name, null, flow);
    }

    @Override
    public Object execute() {
        return null;
    }

    @Override
    public void stop() {
    }
}
