package com.flow.job;

import java.util.UUID;

import com.flow.common.Status;

public interface Job {
    public UUID id();
    
	public void start();
	
	public void interrupt();
	
	public Status status();
	
	public JobConfig config();
}
