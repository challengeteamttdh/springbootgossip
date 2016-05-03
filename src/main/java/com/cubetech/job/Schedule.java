package com.cubetech.job;

import java.util.ArrayList;
import java.util.Collections;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.cubetech.gossip.GossipManager;
import com.google.code.gossip.LocalGossipMember;

@Component
public class Schedule {
	private Logger logger = Logger.getLogger(Schedule.class);
	
	@Scheduled(cron = "${rates.refresh.cron}")
	public void cronJob() {
		logger.info("=============SCHEDULE JOB=============");
		com.google.code.gossip.manager.GossipManager gossipManager = GossipManager.getInstance().getGossipService()
				.get_gossipManager();
		ArrayList<String> listNode = new ArrayList<>();
		listNode.add(gossipManager.getMyself().getPort() + "");
		logger.info("MEMBER:" + gossipManager.getMemberList());
		for (LocalGossipMember lgm : gossipManager.getMemberList()) {
			if (lgm.getHeartbeat() > 0) {
				listNode.add(lgm.getPort() + "");
			}
		}

		Collections.sort(listNode);
		int numNode = listNode.size();
		logger.info("List Current Node = " + listNode);
		int orderNode = 0;
		for (int i = 0; i < listNode.size(); i++) {
			if (gossipManager.getMyself().getPort() == Integer.parseInt(listNode.get(i))) {
				orderNode = i;
				break;
			}
		}
		logger.info("Order = " + orderNode);
		StringBuffer logOrder = new StringBuffer();
		for (int i = 1; i <= 10; i++) {
			if (i % numNode == orderNode) {
				logOrder.append(i + "-");
			}
		}
	}
}
