package episen.si.ing1.pds.backend.server;

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


    // TODO Add a connection in the
    public ClientRequestManager(Socket socket, Connection connection) throws IOException {
        this.connection = connection;
        clientSocket = socket;
//        writer = new PrintWriter(socket.getOutputStream(), true);
//        reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(socket.getInputStream())));
//        self = new Thread(this, name);
//        self.run();
    }

    @Override
    public void run() {

        PrintWriter out = null;
        BufferedReader in = null;
        try {

            // get the outputstream of client
            out = new PrintWriter(
                    clientSocket.getOutputStream(), true);

            // get the inputstream of client
            in = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            ObjectMapper mapper = new ObjectMapper();
            String line;
            while ((line = in.readLine()) != null) {
                RequestSocket request = mapper.readValue(line, RequestSocket.class);
                sendResponse(request, out);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
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
//        byte[] inputData;
//        try {
////            while (inputStream.available() == 0) {
////            }
////            inputData = new byte[inputStream.available()]; // create a byte array with the number of data
////            inputStream.read(inputData);
////
////            String msg = new String(inputData);
//            String msg = reader.readLine();
//            ObjectMapper mapper = new ObjectMapper();
//            logger.info(msg);
//            RequestSocket request = mapper.readValue(msg, RequestSocket.class);
//
//            sendResponse(request);
//
//            //outputStream.write(mapper.writeValueAsBytes(resultQuery(mapper,inputData)));
//            logger.info("Response issued");
//            DataSource.getInstance().putConnection(connection);
//            RequestSocket request = mapper.readValue(msg, RequestSocket.class);
//
//            sendResponse(request);
//
//            logger.info("data received {} bytes, content={}", inputData.length, new String(inputData));
//            //outputStream.write(mapper.writeValueAsBytes(resultQuery(mapper,inputData)));
//            logger.info("Response issued");
//        } catch (Exception e) {
//            try {
//                reader.close();
//                writer.close();
//            } catch (IOException ioException) {
//                ioException.printStackTrace();
//            }
//        }
    }


    public void sendResponse(RequestSocket request, PrintWriter writer) throws Exception {
        String event = request.getRequest();

        if (event.equals("building_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            List<Map> building = new ArrayList<>();
            String sql = "Select distinct(building_id), building_name  from Building Natural Join Floor_ Natural Join Space Natural Join Rental Natural Join Maintenance_Department_Administrators Where company_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            // setInt permits to put value in sql variable.
            statement.setInt(1, (Integer) dataLoaded.get("company_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("building_id", rs.getInt("building_id"));
                hm.put("building_name", rs.getString("building_name"));
                building.add(hm);
            }
            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", event);
            response.put("data", building);

            String responseMsg = mapper.writeValueAsString(response);

            logger.info(responseMsg);
            writer.println(responseMsg);
            logger.info("Response Has been sent");
//            outputStream.write(responseMsg.getBytes(StandardCharsets.UTF_8));

        } else if (event.equals("floor_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            List<Map> floor = new ArrayList<>();
            String sql = "Select Distinct(floor_id), floor_number from Floor_ Natural Join Space Natural Join Rental Natural Join Maintenance_Department_Administrators Where company_id=? and building_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, (Integer) dataLoaded.get("company_id"));
            statement.setInt(2, (Integer) dataLoaded.get("building_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("floor_id", rs.getInt("floor_id"));
                hm.put("floor_number", rs.getInt("floor_number"));
                floor.add(hm);
            }
            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", event);
            response.put("data", floor);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
            //writer.flush();
            //    outputStream.write(responseMsg.getBytes(StandardCharsets.UTF_8));
        }


    }

}
