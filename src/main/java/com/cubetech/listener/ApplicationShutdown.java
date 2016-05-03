package com.cubetech.listener;

import javax.servlet.ServletContextEvent;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextCleanupListener;

import com.cubetech.gossip.GossipManager;

public class ApplicationShutdown extends ContextCleanupListener {
	
	private Logger logger = Logger.getLogger(ApplicationShutdown.class);
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		GossipManager.getInstance().stop();
		logger.info("Gossip Service Shutdown");
	}
}
