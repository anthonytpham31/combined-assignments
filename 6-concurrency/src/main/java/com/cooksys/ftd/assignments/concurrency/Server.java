package com.cooksys.ftd.assignments.concurrency;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import com.cooksys.ftd.assignments.concurrency.model.config.ServerConfig;

public class Server implements Runnable {
	
	// Default variables for ServerConfig - Don't forget one
	private boolean disabled = false;
	private int port = 8080;
	private int maxClients;
	
    public Server(ServerConfig config) {
        this.disabled = config.isDisabled();
        this.port = config.getPort();
        this.maxClients = config.getMaxClients();
    }

    @Override
    public void run() {

		ExecutorService pool = Executors.newFixedThreadPool(maxClients);
		// List<Thread> threadCounter = new ArrayList<>();
		
    	try {
    		ServerSocket serverSockets = new ServerSocket(port);
    		serverSockets.setSoTimeout(5000);
    		for(;;) {
			// Create threads for each set of max clients
			pool.execute(new ClientHandler(serverSockets.accept()));
			serverSockets.close();
			pool.shutdown();
			}
		} catch (IOException e) {
			e.printStackTrace();
			pool.shutdown();
		}	

    }
}
