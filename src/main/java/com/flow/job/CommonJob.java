package com.flow.job;

import java.util.UUID;

import com.flow.common.Status;
import com.flow.recipe.Flow;

public class CommonJob implements Job {
	private UUID id;
	private long timestamp;
	private Flow flow;
	private Status status;
	private JobConfig jobConfig;
	
	public CommonJob(Flow flow, JobConfig jobConfig) {
		this.flow = flow;
		this.jobConfig = jobConfig;
		inititalize();
	}
	
	private void inititalize() {
		id = UUID.randomUUID();
		timestamp = System.currentTimeMillis();
		status = Status.READY;
	}
	
	@Override
	public void start() {
		if (flow != null && (status == Status.READY || status == Status.COMPLETED)) {
			status = Status.RUNNING;
			flow.start();
			status = Status.COMPLETED;
		}
	}

	@Override
	public void interrupt() {
		if (flow != null && status == Status.RUNNING) {
			if (flow.getStatus() == Status.RUNNING) {
				status = Status.INTERRUPTING;
				flow.interrupt();
				status = Status.INTERRUPTED;
			}
		}
	}

	@Override
	public Status status() {
		return status;
	}

	@Override
	public UUID id() {
		return id;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public Flow getFlow() {
		return flow;
	}
	
	public Status getFlowStatus() {
		if (flow != null)
			return flow.getStatus();
		return Status.UNKNOWN;
	}

	public void setJobConfig(JobConfig jobConfig) {
		this.jobConfig = jobConfig;
	}

    @Override
    public JobConfig config() {
        return jobConfig;
    }
}
