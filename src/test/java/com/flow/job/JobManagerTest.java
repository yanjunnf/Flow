package com.flow.job;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.flow.action.Action;
import com.flow.action.DelayAction;
import com.flow.recipe.CommonFlow;
import com.flow.step.ProcessStep;

public class JobManagerTest {
	@Test
	public void testJobManagerWithCommonThreadPool() throws Exception {
		CommonFlow flow = new CommonFlow("Flow1", null);
		Action waitAction = new DelayAction("Action 1", 100);
		ProcessStep step = new ProcessStep("Step1", waitAction, flow, null);
		flow.setRoot(step);
		JobConfig jc = new JobConfig(0, 0);
		CommonJob job = new CommonJob(flow, jc);
		
		JobManager jobManager = new JobManager();
		jobManager.start(job);
		Thread.sleep(1000*10);
	}
	
	@Test
	public void testJobManagerWithScheduledThreadPool() throws Exception {
		CommonFlow flow = new CommonFlow("Flow1", null);
		Action waitAction = new DelayAction("Action 1", 100);
		ProcessStep step = new ProcessStep("Step1", waitAction, flow, null);
		flow.setRoot(step);
		JobConfig jc = new JobConfig(1, 5);
		CommonJob job = new CommonJob(flow, jc);
		
		JobManager jobManager = new JobManager();
		jobManager.start(job);
		
		Thread.sleep(1000*10);
		
		int jobCount = jobManager.getRunningJobsCount();
		assertEquals(jobCount, 1);
		
		jobManager.killJob(job.id());
		Thread.sleep(1000*10);
		jobCount = jobManager.getRunningJobsCount();
		assertEquals(jobCount, 0);
	}
}
