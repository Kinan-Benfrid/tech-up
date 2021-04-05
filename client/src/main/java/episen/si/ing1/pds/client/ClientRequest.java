package episen.si.ing1.pds.client;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import episen.si.ing1.pds.client.config.ClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;


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
        //System.out.println("MapJsonFIlm "+mapJsonFile.toString());
        out.write(mapper.writeValueAsBytes(mapJsonFile));
        logger.debug("Request submitted");
        while (in.available()==0){ }
        inputData = new byte[in.available()];
        in.read(inputData);
        //System.out.println(inputData.length);
        String serverResponse = new String (inputData);
        System.out.println(formatString(serverResponse));

    }

    public void stopConnection() throws IOException {
        clientSocket.close();
        out.close();
        in.close();
    }

    /**
     *
     * @param s response of the server
     * @return the response with a good format
     */
    public String formatString(String s){
        return s.replace('.','\n');
    }


}
