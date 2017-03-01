package com.cooksys.ftd.assignments.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.stream.Stream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.cooksys.ftd.assignments.socket.Utils;
import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.LocalConfig;
import com.cooksys.ftd.assignments.socket.model.Student;

public class Client {

    /**
     * The client should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" and "host" properties of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.RemoteConfig} object to create a socket that connects to
     * a {@link Server} listening on the given host and port.
     *
     * The client should expect the server to send a {@link com.cooksys.ftd.assignments.socket.model.Student} object
     * over the socket as xml, and should unmarshal that object before printing its details to the console.
     */
    public static void main(String[] args) {
    	
    	try {
    		String configFilePath = "./config/config.xml";
    		JAXBContext jaxb = Utils.createJAXBContext(); 
    		
    		Config wholeConfig = Utils.loadConfig(configFilePath,jaxb);
    		int port = wholeConfig.getRemote().getPort();
    		String host = wholeConfig.getRemote().getHost();
			Unmarshaller unmarshall = jaxb.createUnmarshaller();
    		
			Socket clientSocket = new Socket(host, port);

    		BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    		String testString = new String();
    		StringBuffer buffString = new StringBuffer();

    		while((testString = in.readLine()) != null) {
    			//testString = testString + in.readLine();
    			System.out.println(testString);
    			buffString.append(testString);
    		}

    		StringReader readString = new StringReader(buffString.toString());
    		Student studentConfig = (Student) unmarshall.unmarshal(readString);
    		System.out.println(studentConfig.toString());

    		clientSocket.close();
    		in.close();
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
