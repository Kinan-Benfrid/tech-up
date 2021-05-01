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
    }


    public void sendResponse(RequestSocket request, PrintWriter writer) throws Exception {
        String requestName = request.getRequest();

        if (requestName.equals("building_list")) {
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
            response.put("request", requestName);
            response.put("data", building);

            String responseMsg = mapper.writeValueAsString(response);

            logger.info(responseMsg);
            writer.println(responseMsg);
            logger.info("Response Has been sent");
//            outputStream.write(responseMsg.getBytes(StandardCharsets.UTF_8));

        } else if (requestName.equals("floor_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            System.out.println("floor" + dataLoaded);
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
            response.put("request", requestName);
            response.put("data", floor);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);


        } else if (requestName.equals("space_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            System.out.println("space " + dataLoaded);
            List<Map> spaces = new ArrayList<>();
            String sql = "Select Distinct(space_id), space_name, spacetype_id from Space Natural Join Rental Natural Join Maintenance_Department_Administrators Where company_id= ? and floor_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, (Integer) dataLoaded.get("company_id"));
            statement.setInt(2, (Integer) dataLoaded.get("floor_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("space_id", rs.getInt("space_id"));
                hm.put("space_name", rs.getString("space_name"));
                hm.put("space_type", rs.getInt("spacetype_id"));
                spaces.add(hm);
            }
            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", spaces);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("card_list")) {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println("card " + requestName);
            List<Map> card = new ArrayList<>();
            String sql = "select card_id from access_card where affected_card=false;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("card_id", rs.getInt("card_id"));
                card.add(hm);
            }
            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", card);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }


    }

}
