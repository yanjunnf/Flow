package com.flow.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import com.flow.common.Status;
import com.flow.recipe.Flow;

public class FlowManager implements Manager {
    private Map<String, Flow> flowMap;
    private Logger logger = Logger.getLogger(FlowManager.class.toString());
    
    public FlowManager() {
        flowMap = new HashMap<String, Flow>();
    }
    
    public boolean addFlow(Flow flow) {
        boolean result = false;
        synchronized(flowMap) {
            if (!flowMap.containsKey(flow.getName())) {
                flowMap.put(flow.getName(), flow);
                result = true;
            } 
        }
        if (!result)
            logger.severe("The flow has already existed. Flow name =" + flow.getName());
        
        return result;
    }
    
    public void removeFlow(Flow flow) {
        flowMap.remove(flow.getName());
    }
    
    public List<Flow> getRunningFlows() {
        List<Flow> runningFlows = new ArrayList<Flow>();
        synchronized(flowMap) {
            for (String name : flowMap.keySet()) {
                Flow flow = flowMap.get(name);
                if (flow.getStatus() == Status.RUNNING)
                    runningFlows.add(flow);
            }
        }
        
        return runningFlows;
    }

    @Override
    public boolean start(String name) {
        return false;
    }

    @Override
    public boolean delete(String name) {
        boolean result = false;
        synchronized(flowMap) {
            Flow flow = flowMap.get(name);
            if (flow != null) {
                flow.interrupt();
                flowMap.remove(name);
            }
        }
        
        if (!result)
            logger.severe("Cannot find the flow. Name = " + name);
        
        return result;
    }

    @Override
    public boolean interrupt(String name) {
    	Flow flow = null;
    	synchronized(flowMap) {
    		flow = flowMap.get(name);
    	}
		
    	if (flow != null) {
			flow.interrupt();
		}
    	
        return false;
    }

    @Override
    public boolean restart(String name) {
        return false;
    }

    @Override
    public List<Flow> query(Status status) {
        List<Flow> flows = new ArrayList<Flow>();
        synchronized(flowMap) {
            for (String name : flowMap.keySet()) {
                Flow flow = flowMap.get(name);
                if (flow.getStatus() == status)
                    flows.add(flow);
            }
        }
        
        return flows;
    }

    @Override
    public Flow copy(Flow flow) {
        return null;
    }

    @Override
    public Flow get(String name) {
        Flow flow = null;
        synchronized(flowMap) {
            flow = flowMap.get(name);
        }
        
        return flow;
    }
}
