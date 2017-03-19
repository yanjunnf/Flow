package com.flow.job;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;

import org.apache.log4j.Logger;
import com.flow.common.Status;
import com.flow.config.ConfigClass;
import com.flow.runnable.JobCallable;
import com.flow.runnable.JobRunnable;

import java.util.HashMap;
import java.util.Map;

/*
 * The class JobManager is used to manage all submitted jobs. It's responsible to start/stop/insert/delete all jobs
 * */
public class JobManager {
	private Map<UUID, Job> jobMapper;
    private Logger logger = Logger.getLogger(JobManager.class);
    //Thread pools
    private ExecutorService commonThreadPool;
    private ScheduledExecutorService scheduledThreadPool;
	
    public JobManager() {
        jobMapper = new HashMap<UUID, Job>();
        initialize();
	}
    
    private void initialize() {
        commonThreadPool = Executors.newFixedThreadPool(ConfigClass.fixThreadPoolSize);
        scheduledThreadPool = Executors.newScheduledThreadPool(ConfigClass.scheduledThreadPoolSize);
    }
    
    public Map<UUID, Job> getJobMapper() {
        return jobMapper;
    }
    
    private void start(Job job) {
        if (job != null) {
            JobConfig jobConfig = job.config();
            if (jobConfig.getDelay() == 0 && jobConfig.getInterval() == 0) {
                Future<Job> future = commonThreadPool.submit(new JobCallable(job));
                logger.info("Start job in common thread pool. id=" + job.getId());
            } else {
                
            }
        }
    }
    
    public boolean insert(Job job) {
        boolean ret = false;
        if (job != null) {
            synchronized(jobMapper) {
                if (!jobMapper.containsKey(job.getId())) {
                    jobMapper.put(job.getId(), job);
                    start(job);
                    ret = true;
                } else
                    logger.warn("This job is already existed.");
            }
        }
        return ret;
    }
    
    public boolean delete(UUID id, boolean force) {
        boolean ret = false;
        if (id != null) {
            synchronized(jobMapper) {
                if (jobMapper.containsKey(id)) {
                    Job job = jobMapper.get(id);
                    if (force) {
                        job.stop();
                        jobMapper.remove(id);
                        ret = true;
                        logger.info("Removed job. id=" + id);
                    } else if (job.status() != Status.RUNNING){
                        jobMapper.remove(id);
                        ret = true;
                        logger.info("Removed job. id=" + id);
                    } else
                        logger.warn("The job is running, cannot remove it. Please specify 'force' to remove it");
                }
            }
        }
        return ret;
    }
}
 