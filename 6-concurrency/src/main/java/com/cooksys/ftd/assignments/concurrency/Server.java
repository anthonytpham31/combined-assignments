package com.cooksys.ftd.assignments.concurrency;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
		
    	try {
    		while(true) {
			ServerSocket serverSockets = new ServerSocket(port);
			Socket clientSockets = serverSockets.accept();
			serverSockets.setSoTimeout(5000);
			clientSockets.close();
			serverSockets.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}	

    }
}
