package episen.si.ing1.pds.backend.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import episen.si.ing1.pds.backend.server.socket.RequestSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestHandler {
    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class.getName());

    public static void sendResponse(RequestSocket request, PrintWriter writer, Connection connection) throws Exception {
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
            String sql = "select card_id from access_card where affected_card=false ORDER BY card_id ASC;";
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

        } else if (requestName.equals("card_lost")) {
            ObjectMapper mapper = new ObjectMapper();
            System.out.println("card " + requestName);
            List<Map> card = new ArrayList<>();
            String sql = "select card_id from access_card ORDER BY card_id ASC";
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

        } else if (requestName.equals("name_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            System.out.println("name " + dataLoaded);
            System.out.println("name " + requestName);
            List<Map> name = new ArrayList<>();
            String sql = "select person_id, person_firstname, person_surname from person where company_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("company_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("person_id", rs.getInt("person_id"));
                hm.put("person_firstname", rs.getString("person_firstname"));
                hm.put("person_surname", rs.getString("person_surname"));
                name.add(hm);
            }
            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("affected_card")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            System.out.println("data loaded" + request.getData());
            List<Map> name = new ArrayList<>();
            String sql = "UPDATE access_card set active= true ,affected_card = true, person_id =? where card_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,(Integer)dataloaded.get("person_id"));
            statement.setInt(2,(Integer)dataloaded.get("card_id"));
            statement.executeUpdate();
            System.out.println("nb lines updated");

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }
        else if (requestName.equals("maj_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            System.out.println("name " + dataLoaded);
            System.out.println("name " + requestName);
            List<Map> name = new ArrayList<>();
            String sql = "select access_card.person_id,person_firstname,person_surname from person inner join access_card on person.person_id = access_card.person_id where affected_card=true and company_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("company_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("person_id", rs.getInt("person_id"));
                hm.put("person_firstname", rs.getString("person_firstname"));
                hm.put("person_surname", rs.getString("person_surname"));
                name.add(hm);
            }
            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("company_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            List<Map> name = new ArrayList<>();
            String sql = "select company_id, company_name from company";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("company_id", rs.getInt("company_id"));
                hm.put("company_name", rs.getString("company_name"));
                name.add(hm);
            }
            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("clearance_level0")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            System.out.println("data loaded" + request.getData());
            List<Map> name = new ArrayList<>();
            String sql = "UPDATE access_card set clearance_level=0,affected_card = true,person_id =? where card_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,(Integer)dataloaded.get("person_id"));
            statement.setInt(2,(Integer)dataloaded.get("card_id"));
            statement.executeUpdate();
            System.out.println("nb lines updated");

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        } else if (requestName.equals("clearance_level1")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            System.out.println("data loaded" + request.getData());
            List<Map> name = new ArrayList<>();
            String sql = "UPDATE access_card set clearance_level=1,affected_card = true,person_id =? where card_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,(Integer)dataloaded.get("person_id"));
            statement.setInt(2,(Integer)dataloaded.get("card_id"));
            statement.executeUpdate();
            System.out.println("nb lines updated");

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        } else if (requestName.equals("clearance_level2")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            System.out.println("data loaded" + request.getData());
            List<Map> name = new ArrayList<>();
            String sql = "UPDATE access_card set clearance_level=2,affected_card = true,person_id =? where card_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,(Integer)dataloaded.get("person_id"));
            statement.setInt(2,(Integer)dataloaded.get("card_id"));
            statement.executeUpdate();
            System.out.println("nb lines updated");

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        } else if (requestName.equals("clearance_level3")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            System.out.println("data loaded" + request.getData());
            List<Map> name = new ArrayList<>();
            String sql = "UPDATE access_card set clearance_level=3,affected_card = true,person_id =? where card_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,(Integer)dataloaded.get("person_id"));
            statement.setInt(2,(Integer)dataloaded.get("card_id"));
            statement.executeUpdate();
            System.out.println("nb lines updated");

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        } else if (requestName.equals("active_cardT")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            System.out.println("data loaded" + request.getData());
            List<Map> name = new ArrayList<>();
            String sql = "UPDATE access_card set active = true, affected_card = true, person_id =? where card_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,(Integer)dataloaded.get("person_id"));
            statement.setInt(2,(Integer)dataloaded.get("card_id"));
            statement.executeUpdate();
            System.out.println("nb lines updated");

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        } else if (requestName.equals("active_cardF")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            System.out.println("data loaded" + request.getData());
            List<Map> name = new ArrayList<>();
            String sql = "UPDATE access_card set active = false, affected_card = true, person_id =? where card_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1,(Integer)dataloaded.get("person_id"));
            statement.setInt(2,(Integer)dataloaded.get("card_id"));
            statement.executeUpdate();
            System.out.println("nb lines updated");

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        } else if (requestName.equals("dissociate")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            System.out.println("data loaded" + request.getData());
            List<Map> name = new ArrayList<>();
            String sql = "UPDATE access_card set affected_card = false, person_id =null where card_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(2,(Integer)dataloaded.get("card_id"));
            statement.executeUpdate();
            System.out.println("nb lines updated");

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if(requestName.equals ("persons_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            int person_id = (int) dataloaded.get ("person");
            String query = "SELECT e.equipment_name, isUserAccessibleToEquipment(?, e.equipment_id) AS accessible FROM equipment e";
            PreparedStatement statement = connection.prepareStatement (query);
            statement.setInt (1,person_id);
            ResultSet rs = statement.executeQuery ();
            List<Map> list = new ArrayList<> ();
            while(rs.next ()) {
                Map m = new HashMap ();
                m.put("equipment_name",rs.getString ("equipment_name"));
                m.put ("accessible",rs.getString ("accessible"));

                list.add (m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);
            System.out.println (list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }

        else if (requestName.equals("meeting_room")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            System.out.println("meeting room " + dataLoaded);
            List<Map> name= new ArrayList<>();
            String sql = " Select Distinct(space_id),price from space Where spacetype_id = 1 ";
            PreparedStatement statement = connection.prepareStatement(sql);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("space_id", rs.getInt("space_id"));
                // hm.put("space_name", rs.getString("space_name"));
                hm.put("price", rs.getInt("price"));
                // hm.put("max person number", rs.getInt("max_person_number"));
                name.add(hm);
            }
            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("position")) {
                ObjectMapper mapper = new ObjectMapper();
                Map dataLoaded = (Map) request.getData();
                List<Map> position = new ArrayList<>();
                String sql = "select position_id, x_position, y_position, available from position_ where space_id = ?";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, (Integer) dataLoaded.get("space_id"));
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    Map<String, Object> hm = new HashMap<>();
                    hm.put("position_id", rs.getInt("position_id"));
                    hm.put("x_position", rs.getInt("x_position"));
                    hm.put("y_position", rs.getInt("y_position"));
                    hm.put("available", rs.getBoolean("available"));
                    position.add(hm);
                }
                Map<String, Object> response = new HashMap<>();
                response.put("request", requestName);
                response.put("data", position);

                String responseMsg = mapper.writeValueAsString(response);
                writer.println(responseMsg);
        }
    }
}
