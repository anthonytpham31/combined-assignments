package com.cooksys.ftd.assignments.concurrency;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.cooksys.ftd.assignments.concurrency.model.message.Request;
import com.cooksys.ftd.assignments.concurrency.model.message.RequestType;
import com.cooksys.ftd.assignments.concurrency.model.message.Response;

public class ClientHandler implements Runnable {

    private Socket clientHandlerSocket;
	
	public ClientHandler (Socket clientHandlerSocket) {
		this.clientHandlerSocket = clientHandlerSocket;
	}
    
	@Override
    public void run() {
		
    	Response serverResponse = new Response();
		String respoData = serverResponse.getData();
		
		RequestType reqTypeRespo = serverResponse.getType();

		try {
			JAXBContext jaxb = JAXBContext.newInstance(Response.class, Request.class, RequestType.class);
			BufferedReader handlerReader = new BufferedReader(new InputStreamReader(clientHandlerSocket.getInputStream()));
			
			OutputStreamWriter fStreamOut = new OutputStreamWriter(clientHandlerSocket.getOutputStream());
	    	BufferedWriter out = new BufferedWriter(fStreamOut);
	    		
	    	Marshaller marshalOut = jaxb.createMarshaller();
	    	marshalOut.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    	marshalOut.marshal(respoData, out);	
			
		} catch (JAXBException | IOException e) {
			e.printStackTrace();
		}
	}
	
}
