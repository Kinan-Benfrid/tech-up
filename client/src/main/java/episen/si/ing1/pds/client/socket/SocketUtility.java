package episen.si.ing1.pds.client.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import episen.si.ing1.pds.client.config.ClientConfig;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class SocketUtility {
    private Socket socket;
    private final ObjectMapper mapper = new ObjectMapper();

    public SocketUtility() {
        try {
            ClientConfig config = new ClientConfig();
            socket = new Socket(config.getConfig().getIpAddress(), config.getConfig().getListenPort());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResponseSocket sendRequest(RequestSocket request) {
        ResponseSocket responseSocket = null;
        try {
            String requestStr = mapper.writeValueAsString(request);

            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            writer.println(requestStr);

            String msg = reader.readLine();

            responseSocket = mapper.readValue(msg, ResponseSocket.class);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseSocket;
    }
}
