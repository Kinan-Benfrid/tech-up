package episen.si.ing1.pds.client.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import episen.si.ing1.pds.client.config.ClientConfig;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketUtility {
    private Socket socket = SocketFactory.Instance.getSocket();
    private final ObjectMapper mapper = new ObjectMapper();


    public ResponseSocket sendRequest(RequestSocket request) {
        ResponseSocket responseSocket = null;
        try {
            String requestStr = mapper.writeValueAsString(request);

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer.println(requestStr);

            String msg = reader.readLine();
            System.out.println(msg);
            ResponseSocket responseS = mapper.readValue(msg, ResponseSocket.class);
            //System.out.println("Request " +responseS.getRequest());
            System.out.println(responseS.getData());
            if(responseS.getRequest().equals("empty_pool")) {
                throw new IllegalAccessException(responseS.getData().toString());
            }
            responseSocket = responseS;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseSocket;
    }

    public void closeSocket(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
