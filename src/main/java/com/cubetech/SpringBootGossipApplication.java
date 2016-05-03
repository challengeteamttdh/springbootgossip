package com.cubetech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.cubetech.gossip.GossipManager;

@SpringBootApplication
@EnableScheduling
public class SpringBootGossipApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootGossipApplication.class, args);
		GossipManager.getInstance().start();
	}
}
