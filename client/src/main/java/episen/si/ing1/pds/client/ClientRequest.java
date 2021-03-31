package episen.si.ing1.pds.client;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import episen.si.ing1.pds.backend.server.ServerCore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class ClientRequest {
    private Socket clientSocket;
    private InputStream in;
    private OutputStream out;
    private static final Logger logger = LoggerFactory.getLogger(ClientRequest.class.getName());
    private static String clientDataFileLocation;// Déclaration du fichier Json
    private static String clientDataEnvVar = "CLIENT_DATA_JSON";


    public ClientRequest(final ClientConfig config ) throws IOException {
        //the client socket take in parametter, the id adress and the port
        clientSocket = new Socket(config.getConfig().getIpAddress(), config.getConfig().getListenPort());
        clientDataFileLocation = System.getenv(clientDataEnvVar);// Json file. mehdi created this file in another place
    }

    public void startConnection() throws IOException, ClassNotFoundException {
        out = clientSocket.getOutputStream(); // it's use to send the information
        in = clientSocket.getInputStream();// received of information
        final ObjectMapper mapper = new ObjectMapper(new JsonFactory());// initialisation of mapper object
        Student mapJsonFile = mapper.readValue(new File(clientDataFileLocation), Student.class);//Created the student object
        System.out.println("MapJsonFIlm "+mapJsonFile.toString());
        out.write(mapper.writeValueAsBytes(mapJsonFile));// send of this object (Student)
        logger.debug("request submitted");// logger replace println
    }

    public void stopConnection() throws IOException {
        clientSocket.close();
        out.close();
        in.close();
    }




}
