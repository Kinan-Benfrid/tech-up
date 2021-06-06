package episen.si.ing1.pds.backend.server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import episen.si.ing1.pds.backend.server.model.Crud;
import episen.si.ing1.pds.backend.server.pool.DataSource;
import episen.si.ing1.pds.backend.server.socket.RequestSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ClientRequestManager implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ClientRequestManager.class.getName());
    private final static String name = "client-thread-manager";
    private Connection connection;
    private Socket clientSocket;


    public ClientRequestManager(Socket socket, Connection connection) throws IOException {
        this.connection = connection;
        clientSocket = socket;
    }

    @Override
    public void run() {

        PrintWriter out = null;
        BufferedReader in = null;
        try {
            // get the outputstream of client
            out = new PrintWriter(clientSocket.getOutputStream(), true);

            // get the inputstream of client
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ObjectMapper mapper = new ObjectMapper();
            String line;
            while ((line = in.readLine()) != null) {
                RequestSocket request = mapper.readValue(line, RequestSocket.class);
                if(connection != null)
                    RequestHandler.sendResponse(request, out,connection);
                else {
                    System.out.println("DataSource : " + DataSource.getInstance().getNbConnection());
                    handleReachedLimitPool(out);
                }
            }

        }
        catch (Exception e) {
            logger.error("client has been disconnected");
            logger.error (e.getLocalizedMessage (), e);
        }
        finally {
            /**
             * if the client has finished with the connection, we put back the connection in the pool
             */
            if (connection!=null) {
                DataSource.getInstance().putConnection(connection);
                logger.info("nb connection after closing client : " + DataSource.getInstance().getNbConnection());
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                    clientSocket.close();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleReachedLimitPool(PrintWriter writer) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Map<String, Object> response = new HashMap<>();
        response.put("request", "empty_pool");
        response.put("data", "there is no more connection in the pool. Retry later.");

        String errorMsg = mapper.writeValueAsString(response);
        writer.println(errorMsg);
    }

}
