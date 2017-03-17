package com.flow.job;

public class SchedulerJobConfig implements JobConfig{
	@Override
	public RUNWAY getRunWay() {
		return RUNWAY.SCHEDULER;
	}
}
