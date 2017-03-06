package com.cooksys.ftd.assignments.concurrency.model.config;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;

import com.cooksys.ftd.assignments.concurrency.ClientInstance;

import java.io.File;
import java.nio.file.Path;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Config {

    /**
     * Loads a {@link Config} object from the given xml file path
     *
     * @param path the path at which an xml configuration can be found
     * @return the unmarshalled {@link Config} object
     */
    public static Config load(Path path) {
    	
    	try {
    		JAXBContext jaxb = JAXBContext.newInstance(ClientConfig.class, ClientInstance.class, Config.class, ServerConfig.class);
			File configurationFile = new File(path.toString());
			Unmarshaller unmarshall= jaxb.createUnmarshaller();
			Config configObject = (Config) unmarshall.unmarshal(configurationFile);
			return configObject;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		
		return null;
    }

    /**
     * server configuration
     */
    private ServerConfig server;

    /**
     * client configuration
     */
    private ClientConfig client;

    public ServerConfig getServer() {
        return server;
    }

    public void setServer(ServerConfig server) {
        this.server = server;
    }

    public ClientConfig getClient() {
        return client;
    }

    public void setClient(ClientConfig client) {
        this.client = client;
    }
}
