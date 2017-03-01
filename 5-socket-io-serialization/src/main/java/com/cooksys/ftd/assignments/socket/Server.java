package com.cooksys.ftd.assignments.socket;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.LocalConfig;
import com.cooksys.ftd.assignments.socket.model.Student;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class Server extends Utils {

    /**
     * Reads a {@link Student} object from the given file path
     *
     * @param studentFilePath the file path from which to read the student config file
     * @param jaxb the JAXB context to use during unmarshalling
     * @return a {@link Student} object unmarshalled from the given file path
     */
    public static Student loadStudent(String studentFilePath, JAXBContext jaxb) {
        
		try {
			
			File studentFile = new File(studentFilePath);			
			Unmarshaller unmarshall = jaxb.createUnmarshaller();
			Student studentConfig = (Student) unmarshall.unmarshal(studentFile);
			return studentConfig;
			
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;
    }

    /**
     * The server should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" property of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.LocalConfig} object to create a server socket that
     * listens for connections on the configured port.
     *
     * Upon receiving a connection, the server should unmarshal a {@link Student} object from a file location
     * specified by the config's "studentFilePath" property. It should then re-marshal the object to xml over the
     * socket's output stream, sending the object to the client.
     *
     * Following this transaction, the server may shut down or listen for more connections.
     */
    public static void main(String[] args) {
        
    	try {

        	String configFilePath = "C:/Users/student-4/combined-assignments/5-socket-io-serialization/config/config.xml";
        	JAXBContext jaxb = Utils.createJAXBContext(); 
        	
        	Config wholeConfig = Utils.loadConfig(configFilePath,jaxb);	
        	Student studentConfig = loadStudent(wholeConfig.getStudentFilePath(),jaxb);
        	LocalConfig localConfig = wholeConfig.getLocal();
        		
        	ServerSocket serverSocket = new ServerSocket(localConfig.getPort());
    		Socket clientSocket = serverSocket.accept();   		
    		
        	OutputStreamWriter fStreamOut = new OutputStreamWriter(clientSocket.getOutputStream());
        	BufferedWriter out = new BufferedWriter(fStreamOut);
        		
        	Marshaller marshalOut = jaxb.createMarshaller();
        	marshalOut.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        	marshalOut.marshal(studentConfig, out);;
        	
        	out.flush();
    		clientSocket.close();
    		serverSocket.close();
    		out.close();	
    		
    		} catch (IOException | JAXBException e) {
    			e.printStackTrace();
    		}
    	
    }
}
