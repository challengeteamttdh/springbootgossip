/**
 * 
 */
package com.cubetech.gossip;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.springframework.stereotype.Component;

import com.google.code.gossip.GossipService;
import com.google.code.gossip.StartupSettings;

/**
 * @author Administrator
 *
 */
@Component
public class GossipManager {
	private static GossipManager instance = null;
	private GossipService gossipService;
	private StartupSettings startupSettings;
	private Logger logger = Logger.getLogger(GossipManager.class);

	private GossipManager() {
		try {
			startupSettings = StartupSettings.fromJSONFile(new File("gossip.conf"));
			logger.info("Port = " + startupSettings.getPort());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static GossipManager getInstance() {
		if (instance == null) {
			instance = new GossipManager();
		}
		return instance;
	}

	public void start() {
		try {
			gossipService = new GossipService(startupSettings);
			gossipService.start();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void stop() {
		gossipService.shutdown();
	}

	public GossipService getGossipService() {
		return gossipService;
	}

	public void setGossipService(GossipService gossipService) {
		this.gossipService = gossipService;
	}

}
