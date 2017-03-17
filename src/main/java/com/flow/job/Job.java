package com.flow.job;

import com.flow.common.Status;

public interface Job {
	public void start();
	
	public void stop();
	
	public Status status();
}
