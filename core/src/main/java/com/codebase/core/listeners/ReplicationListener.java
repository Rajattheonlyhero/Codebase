package com.codebase.core.listeners;

import com.day.cq.replication.ReplicationAction;
import org.apache.sling.event.jobs.JobManager;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;


@Component(
        service = {EventHandler.class},
        immediate = true,
        property = {
                EventConstants.EVENT_TOPIC + "=" + ReplicationAction.EVENT_TOPIC
        }

)
public class ReplicationListener implements EventHandler{

        private static final Logger logger = LoggerFactory.getLogger(ReplicationListener.class);
        private static final String TOPIC = "com/codebase/core/replication";

        @Reference
        private JobManager jobManager;

        @Override
        public void handleEvent(Event event) {
                logger.info("================ Replication event triggered ==========");
                ReplicationAction action = ReplicationAction.fromEvent(event);
                if (action != null && action.getPath()!=null){
                    try {
                            logger.info("======= Replication received for path ======" + action.getPath());
                            if(jobManager!=null){
                                HashMap<String,Object> jobprops = new HashMap<String, Object>();
                                jobprops.put("PATH", action.getPath());
                                jobManager.addJob(TOPIC, jobprops);
                                logger.info("===== Job Added to the Topic {} : with payload : {} ======",TOPIC, action.getPath() );
                            }
                            else{
                                logger.error("=== Jobmanager is null===");
                            }
                    }
                    catch (Exception e) {
                        logger.error("Error creating job : {} ", e.getMessage(),e);
                        throw new RuntimeException(e);
                    }
                }
                else {
                    logger.error("Replication Action or path is null");
                }

        }
}