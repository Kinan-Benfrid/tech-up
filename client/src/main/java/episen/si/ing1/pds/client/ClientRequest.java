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

import static java.lang.Thread.sleep;

public class ClientRequest {
    private Socket clientSocket;
    private InputStream in;
    private OutputStream out;
    private static final Logger logger = LoggerFactory.getLogger(ClientRequest.class.getName());
    private static String clientDataFileLocation;
    private static String clientDataEnvVar = "CLIENT_DATA_JSON";


    public ClientRequest(final ClientConfig config ) throws IOException {
        clientSocket = new Socket(config.getConfig().getIpAddress(), config.getConfig().getListenPort());
        clientDataFileLocation = System.getenv(clientDataEnvVar);
    }

    public void startConnection() throws IOException, ClassNotFoundException, InterruptedException {
        out = clientSocket.getOutputStream();
        in = clientSocket.getInputStream();
        byte[] inputData;
        final ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        Student mapJsonFile = mapper.readValue(new File(clientDataFileLocation), Student.class);
        System.out.println("MapJsonFIlm "+mapJsonFile.toString());
        out.write(mapper.writeValueAsBytes(mapJsonFile));
        logger.debug("Request submitted");
        System.out.println("dqsfqf");
        while (in.available()==0){
            System.out.println("Boucle while");
        }
        inputData = new byte[in.available()];
        in.read(inputData);
        System.out.println(inputData.length);
        String serverResponse = new String (inputData);
        System.out.println(serverResponse);


    }

    public void stopConnection() throws IOException {
        clientSocket.close();
        out.close();
        in.close();
    }



}
