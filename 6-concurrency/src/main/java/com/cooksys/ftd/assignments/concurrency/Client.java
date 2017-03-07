package com.cooksys.ftd.assignments.concurrency;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

import com.cooksys.ftd.assignments.concurrency.model.config.ClientConfig;
import com.cooksys.ftd.assignments.concurrency.model.config.ClientInstanceConfig;
import com.cooksys.ftd.assignments.concurrency.model.config.SpawnStrategy;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Client implements Runnable {
	
	// Default variables for ClientConfig - don't forget one
	private boolean disabled = false;
	private int port = 8080;
	private String host = "localhost";
	private int maxInstances = -1;
	private SpawnStrategy spawnStrategy = SpawnStrategy.NONE;
	private List<ClientInstanceConfig> instances;
	
    public Client(ClientConfig config) {
        this.disabled = config.isDisabled();
        this.port = config.getPort();
        this.host = config.getHost();
        this.maxInstances = config.getMaxInstances();
        this.spawnStrategy = config.getSpawnStrategy();
        this.instances = config.getInstances(); 
    }

    @Override
    public void run() {

    	try {
    		int numThreads = 0;
    		
    		while(numThreads < maxInstances) {
    			
    		}
			Socket clientSocket = new Socket(host, port);
			//ClientInstanceConfig multiClientInstances = instances.get()
			//ClientInstance mainClientInstance = new ClientInstance(multiClientInstances, clientSocket);
			
			for (;;) {
				
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    }
}
