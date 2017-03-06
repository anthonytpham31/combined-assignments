package com.cooksys.ftd.assignments.concurrency;

import java.util.List;

import com.cooksys.ftd.assignments.concurrency.model.config.ClientInstanceConfig;
import com.cooksys.ftd.assignments.concurrency.model.message.Request;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class ClientInstance implements Runnable {

    // Default variables for ClientIntanceConfig - Don't forget one
	private int delay = -1;
	private List<Request> requests;
	
	public ClientInstance(ClientInstanceConfig config, Socket socket) {
        this.delay = config.getDelay();
        this.requests = config.getRequests();
    }

    @Override
    public void run() {
        throw new NotImplementedException();
    }
}
