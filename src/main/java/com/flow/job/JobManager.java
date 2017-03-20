package com.flow.job;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.flow.config.ConfigClass;
import com.flow.runnable.JobCallable;
import com.flow.runnable.JobRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
 * The class JobManager is used to manage all submitted jobs. It's responsible to start/stop/insert/delete all jobs
 * */
public class JobManager {
	private Map<Future<?>, Job> jobMapper;
    private Logger logger =Logger.getLogger(JobManager.class.toString());
    //Thread pools
    private ExecutorService commonThreadPool;
    private ScheduledExecutorService scheduledThreadPool;
	
    public JobManager() {
        jobMapper = new HashMap<Future<?>, Job>();
        initialize();
	}
    
    private void initialize() {
        commonThreadPool = Executors.newFixedThreadPool(ConfigClass.fixThreadPoolSize);
        scheduledThreadPool = Executors.newScheduledThreadPool(ConfigClass.scheduledThreadPoolSize);
        checkingJobs();
    }
    
    public Map<Future<?>, Job> getJobMapper() {
        return jobMapper;
    }
    
    public void start(Job job) {
        if (job != null) {
            JobConfig jobConfig = job.config();
            if (jobConfig.getDelay() == 0 && jobConfig.getPeriod() == 0) {
                Future<Job> future = commonThreadPool.submit(new JobCallable(job));
                synchronized(jobMapper) {
                	jobMapper.put(future, job);
                }
                logger.info("Start job in common thread pool. id=" + job.id());
            } else {
            	ScheduledFuture<?> future = scheduledThreadPool.scheduleAtFixedRate(new JobRunnable(job), jobConfig.getDelay(), jobConfig.getPeriod(), TimeUnit.SECONDS);
            	jobMapper.put(future, job);
            	logger.info("Start job in scheduled thread pool. id=" + job.id());
            }
        }
    }
    
    public void checkingJobs() {
    	new Thread(new Runnable() {
    		public void run() {
    			while (true) {
    				Map<Future<?>, Job> newJobMapper = new HashMap<Future<?>, Job>();
    				if (!jobMapper.isEmpty()) {
        	    		synchronized(jobMapper) {
        	    			for (Future<?> future : jobMapper.keySet()) {
        	        			Job job = jobMapper.get(future);
        	        			if (future.isCancelled()) {
        	        				logger.info("Job is canceled. id=" + job.id());
        	        			} else if (future.isDone()) {
        	        				logger.info("Job is finished. id=" + job.id());
        	        			} else
        	        				newJobMapper.put(future, job);
        	        		}
        	    			jobMapper = newJobMapper;
        	    		}
        	    	}
    				try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
    			}
    		}
    	}).start();
    }
    
    public int getRunningJobsCount() {
    	return jobMapper.size();
    }
    
    public void killJob(UUID id) {
    	if (jobMapper.size() > 0) {
    		for (Future<?> future : jobMapper.keySet()) {
    			Job job = jobMapper.get(future);
    			if (job.id().equals(id)) {
    				job.interrupt();
    				future.cancel(true);
    				break;
    			}
    		}
    	}
    }
}
 