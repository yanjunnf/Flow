package com.flow.job;

public class OnceJobConfig implements JobConfig{

	@Override
	public RUNWAY getRunWay() {
		return RUNWAY.ONCE;
	}
}
