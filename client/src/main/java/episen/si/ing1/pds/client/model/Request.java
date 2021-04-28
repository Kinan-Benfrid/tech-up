package episen.si.ing1.pds.client.model;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import episen.si.ing1.pds.client.config.ClientConfig;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Request {
    private ClientConfig clientConfig = new ClientConfig();


    public Request() throws IOException {
    }

    public String selectRequest(){
        try {
            Socket clientSocket = new Socket(clientConfig.getConfig().getIpAddress(), clientConfig.getConfig().getListenPort());
            OutputStream out = clientSocket.getOutputStream();
            InputStream in = clientSocket.getInputStream();
            byte[] inputData;
            final ObjectMapper mapper = new ObjectMapper(new JsonFactory());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
