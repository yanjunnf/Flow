package com.flow.job;

import java.util.UUID;

import com.flow.common.Status;

public interface Job {
    public UUID getId();
    
	public void start();
	
	public void stop();
	
	public Status status();
	
	public JobConfig config();
}
