package com.flow;

import org.junit.Test;

import com.flow.action.Action;
import com.flow.action.CommandlineAction;
import com.flow.recipe.CommonFlow;
import com.flow.step.ProcessStep;

public class CommonFlowTest {
    @Test
    public void testFlow() {
        CommonFlow flow = new CommonFlow("Flow1", null);
        Action action1 = new CommandlineAction("Action1", "ping www.baidu.com", false, "GBK");
        Action action2 = new CommandlineAction("Action2", "ping www.sohu.com", false, "GBK");
        ProcessStep step1 = new ProcessStep("Step1", action1, flow, null);
        ProcessStep step2 = new ProcessStep("Step2", action2, flow, step1);
        
        flow.setRoot(step2);
        
        flow.start();
    }
}
