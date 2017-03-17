package com.flow.job;

/*
 * Run job at time xxxxx
 * */
public class TimingJobConfig implements JobConfig {
	private long timestamp;
	
	public TimingJobConfig(int timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public RUNWAY getRunWay() {
		return RUNWAY.TIMING;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
