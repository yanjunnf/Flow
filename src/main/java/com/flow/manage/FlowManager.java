package com.flow.manage;

import java.util.ArrayList;
import java.util.List;

import com.flow.common.Status;
import com.flow.process.Flow;

public class FlowManager {
    private List<Flow> flows;
    
    public FlowManager() {
        flows = new ArrayList<Flow>();
    }
    
    public void addFlow(Flow flow) {
        if (flow != null)
            flows.add(flow);
    }
    
    public void removeFlow(Flow flow) {
        for (Flow fl : flows) {
            if (fl.equals(flow)) {
                flows.remove(fl);
                break;
            }
        }
    }
    
    public List<Flow> getRunningFlows() {
        List<Flow> runningFlows = new ArrayList<Flow>();
        for (Flow flow : flows) {
            if (flow.getStatus() == Status.RUNNING)
                runningFlows.add(flow);
        }
        return runningFlows;
    }
}
