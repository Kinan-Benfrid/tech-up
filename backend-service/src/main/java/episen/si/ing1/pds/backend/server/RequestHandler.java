package episen.si.ing1.pds.backend.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import episen.si.ing1.pds.backend.server.socket.RequestSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.*;



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


        }

        else if (requestName.equals("floor_list")) {
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
            statement.setInt(1, (Integer) dataloaded.get("person_id"));
            statement.setInt(2, (Integer) dataloaded.get("card_id"));
            statement.executeUpdate();
            System.out.println("nb lines updated");

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        } else if (requestName.equals("maj_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            System.out.println("name " + dataLoaded);
            System.out.println("name " + requestName);
            List<Map> name = new ArrayList<>();
            String sql = "select card_id, access_card.person_id,person_firstname,person_surname from person inner join access_card on person.person_id = access_card.person_id where affected_card=true and company_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("company_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("person_id", rs.getInt("person_id"));
                hm.put("card_id", rs.getInt("card_id"));
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
            String sql2 ="insert into card_spacetype (card_id,spacetype_id) values (?,1)";
            String sql3 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,3)";
            String sql4 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,6)";
            String sql5 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,7)";
            String sql6 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,10)";
            PreparedStatement statement1 = connection.prepareStatement(sql);
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            PreparedStatement statement3 = connection.prepareStatement(sql3);
            PreparedStatement statement4 = connection.prepareStatement(sql4);
            PreparedStatement statement5 = connection.prepareStatement(sql5);
            PreparedStatement statement6 = connection.prepareStatement(sql6);
            statement1.setInt(1, (Integer) dataloaded.get("person_id"));
            statement1.setInt(2, (Integer) dataloaded.get("card_id"));
            statement2.setInt(1, (Integer) dataloaded.get("card_id"));
            statement3.setInt(1, (Integer) dataloaded.get("card_id"));
            statement4.setInt(1, (Integer) dataloaded.get("card_id"));
            statement5.setInt(1, (Integer) dataloaded.get("card_id"));
            statement6.setInt(1, (Integer) dataloaded.get("card_id"));
            //statement3.setInt(2, (Integer) dataloaded.get("card_id"));
            //statement3.setInt(3, (Integer) dataloaded.get("card_id"));
            statement1.executeUpdate();
            statement2.executeUpdate ();
            statement3.executeUpdate ();
            statement4.executeUpdate ();
            statement5.executeUpdate ();
            statement6.executeUpdate ();

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
            String sql1 = "UPDATE access_card set clearance_level=1,affected_card = true,person_id =? where card_id=?";
            String sql2 ="insert into card_spacetype (card_id,spacetype_id) values (?,1)";
            String sql3 ="insert into card_spacetype (card_id,spacetype_id) values (?,2)";
            String sql4 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,3)";
            String sql5 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,6)";
            String sql6 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,7)";
            String sql7 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,10)";
            String sql8 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,2)";
            String sql9 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,4)";
            //String sql10 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,7)";
            //String sql11 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,7)";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            PreparedStatement statement3 = connection.prepareStatement(sql3);
            PreparedStatement statement4 = connection.prepareStatement(sql4);
            PreparedStatement statement5 = connection.prepareStatement(sql5);
            PreparedStatement statement6 = connection.prepareStatement(sql6);
            PreparedStatement statement7 = connection.prepareStatement(sql7);
            PreparedStatement statement8 = connection.prepareStatement(sql8);
            PreparedStatement statement9 = connection.prepareStatement(sql9);
            //PreparedStatement statement10 = connection.prepareStatement(sql10);
            //PreparedStatement statement11 = connection.prepareStatement(sql11);
            statement1.setInt(1, (Integer) dataloaded.get("person_id"));
            statement1.setInt(2, (Integer) dataloaded.get("card_id"));
            statement2.setInt(1, (Integer) dataloaded.get("card_id"));
            statement3.setInt(1, (Integer) dataloaded.get("card_id"));
            statement4.setInt(1, (Integer) dataloaded.get("card_id"));
            statement5.setInt(1, (Integer) dataloaded.get("card_id"));
            statement6.setInt(1, (Integer) dataloaded.get("card_id"));
            statement7.setInt(1, (Integer) dataloaded.get("card_id"));
            statement8.setInt(1, (Integer) dataloaded.get("card_id"));
            //statement10.setInt(1, (Integer) dataloaded.get("card_id"));
            //statement11.setInt(1, (Integer) dataloaded.get("card_id"));
            statement1.executeUpdate();
            statement2.executeUpdate ();
            statement3.executeUpdate ();
            statement4.executeUpdate ();
            statement5.executeUpdate ();
            statement6.executeUpdate ();
            statement7.executeUpdate ();
            statement8.executeUpdate ();
            statement9.executeUpdate ();
            //statement10.executeUpdate ();
            //statement11.executeUpdate ();
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
            String sql1 = "UPDATE access_card set clearance_level=2,affected_card = true,person_id =? where card_id=?";
            String sql2 ="insert into card_spacetype (card_id,spacetype_id) values (?,1)";
            String sql3 ="insert into card_spacetype (card_id,spacetype_id) values (?,3)";
            String sql4 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,3)";
            String sql5 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,6)";
            String sql6 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,7)";
            String sql7 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,10)";
            String sql8 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,2)";
            String sql9 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,4)";
            String sql10 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,8)";
            String sql11 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,1)";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            PreparedStatement statement3 = connection.prepareStatement(sql3);
            PreparedStatement statement4 = connection.prepareStatement(sql4);
            PreparedStatement statement5 = connection.prepareStatement(sql5);
            PreparedStatement statement6 = connection.prepareStatement(sql6);
            PreparedStatement statement7 = connection.prepareStatement(sql7);
            PreparedStatement statement8 = connection.prepareStatement(sql8);
            PreparedStatement statement9 = connection.prepareStatement(sql9);
            PreparedStatement statement10 = connection.prepareStatement(sql10);
            PreparedStatement statement11 = connection.prepareStatement(sql11);
            statement1.setInt(1, (Integer) dataloaded.get("person_id"));
            statement1.setInt(2, (Integer) dataloaded.get("card_id"));
            statement2.setInt(1, (Integer) dataloaded.get("card_id"));
            statement3.setInt(1, (Integer) dataloaded.get("card_id"));
            statement4.setInt(1, (Integer) dataloaded.get("card_id"));
            statement5.setInt(1, (Integer) dataloaded.get("card_id"));
            statement6.setInt(1, (Integer) dataloaded.get("card_id"));
            statement7.setInt(1, (Integer) dataloaded.get("card_id"));
            statement8.setInt(1, (Integer) dataloaded.get("card_id"));
            statement10.setInt(1, (Integer) dataloaded.get("card_id"));
            statement11.setInt(1, (Integer) dataloaded.get("card_id"));
            statement1.executeUpdate();
            statement2.executeUpdate ();
            statement3.executeUpdate ();
            statement4.executeUpdate ();
            statement5.executeUpdate ();
            statement6.executeUpdate ();
            statement7.executeUpdate ();
            statement8.executeUpdate ();
            statement9.executeUpdate ();
            statement10.executeUpdate ();
            statement11.executeUpdate ();
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
            String sql1 = "UPDATE access_card set clearance_level=3,affected_card = true,person_id =? where card_id=?";
            String sql2 ="insert into card_spacetype (card_id,spacetype_id) values (?,1)";
            String sql3 ="insert into card_spacetype (card_id,spacetype_id) values (?,2)";
            String sql4 ="insert into card_spacetype (card_id,spacetype_id) values (?,3)";
            String sql5 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,3)";
            String sql6 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,6)";
            String sql7 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,7)";
            String sql8 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,10)";
            String sql9 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,2)";
            String sql10 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,4)";
            String sql11 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,8)";
            String sql12 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,1)";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            PreparedStatement statement3 = connection.prepareStatement(sql3);
            PreparedStatement statement4 = connection.prepareStatement(sql4);
            PreparedStatement statement5 = connection.prepareStatement(sql5);
            PreparedStatement statement6 = connection.prepareStatement(sql6);
            PreparedStatement statement7 = connection.prepareStatement(sql7);
            PreparedStatement statement8 = connection.prepareStatement(sql8);
            PreparedStatement statement9 = connection.prepareStatement(sql9);
            PreparedStatement statement10 = connection.prepareStatement(sql10);
            PreparedStatement statement11 = connection.prepareStatement(sql11);
            PreparedStatement statement12 = connection.prepareStatement(sql12);
            statement1.setInt(1, (Integer) dataloaded.get("person_id"));
            statement1.setInt(2, (Integer) dataloaded.get("card_id"));
            statement2.setInt(1, (Integer) dataloaded.get("card_id"));
            statement3.setInt(1, (Integer) dataloaded.get("card_id"));
            statement4.setInt(1, (Integer) dataloaded.get("card_id"));
            statement5.setInt(1, (Integer) dataloaded.get("card_id"));
            statement6.setInt(1, (Integer) dataloaded.get("card_id"));
            statement7.setInt(1, (Integer) dataloaded.get("card_id"));
            statement8.setInt(1, (Integer) dataloaded.get("card_id"));
            statement10.setInt(1, (Integer) dataloaded.get("card_id"));
            statement11.setInt(1, (Integer) dataloaded.get("card_id"));
            statement12.setInt(1, (Integer) dataloaded.get("card_id"));
            statement1.executeUpdate();
            statement2.executeUpdate ();
            statement3.executeUpdate ();
            statement4.executeUpdate ();
            statement5.executeUpdate ();
            statement6.executeUpdate ();
            statement7.executeUpdate ();
            statement8.executeUpdate ();
            statement9.executeUpdate ();
            statement10.executeUpdate ();
            statement11.executeUpdate ();
            statement12.executeUpdate ();
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
            statement.setInt(1, (Integer) dataloaded.get("person_id"));
            statement.setInt(2, (Integer) dataloaded.get("card_id"));
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
            statement.setInt(1, (Integer) dataloaded.get("person_id"));
            statement.setInt(2, (Integer) dataloaded.get("card_id"));
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
            String sql = "UPDATE access_card set affected_card = false,clearance_level=null person_id =null where card_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataloaded.get("card_id"));
            statement.executeUpdate();
            System.out.println("nb lines updated");

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("persons_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            int person_id = (int) dataloaded.get("person");
            String query = "SELECT e.equipment_name, isUserAccessibleToEquipment(?, e.equipment_id) AS accessible FROM equipment e";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, person_id);
            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
                m.put("equipment_name", rs.getString("equipment_name"));
                m.put("accessible", rs.getString("accessible"));

                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);
            System.out.println(list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("spaceP_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            int person_id = (int) dataloaded.get("person");
            String query = "SELECT s.space_name, AccessToSpace(?, s.space_id) as accessible FROM space s";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, person_id);
            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
                m.put("space_name", rs.getString("space_name"));
                m.put("accessible", rs.getString("accessible"));

                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);
            System.out.println(list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);


        } else if (requestName.equals("spaceA_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            // int person_id = (int) dataloaded.get ("person");
            String query = "Select space_name,size_space, max_person_number,floor_number, building_name,price from space INNER JOIN floor_ ON space.floor_id = floor_.floor_id INNER JOIN building ON floor_.building_id = building.building_id  Where rental_id is NULL";
            PreparedStatement statement = connection.prepareStatement(query);
            //statement.setInt (1,person_id);
            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
                m.put("space_name", rs.getString("space_name"));
                m.put("size_space", rs.getInt("size_space"));
                m.put("max_person_number", rs.getInt("max_person_number"));
                m.put("floor_number", rs.getInt("floor_number"));
                m.put("building_name", rs.getString("building_name"));
                m.put("price", rs.getInt("price"));

                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);
            System.out.println(list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }

        else if (requestName.equals("meeting_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
           // int nombre  = Space.getnumberPerson;
            // int person_id = (int) dataloaded.get ("person");
            String query = "SELECT count(space_id) as countable from space where spacetype_id = 1 and rental_id is NULL";
           // String query = "SELECT count(space_id) as countable from space where spacetype_id>=" + nombre + "  and rental_id is NULL";
            PreparedStatement statement = connection.prepareStatement(query);
            //statement.setInt (1,person_id);
            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
                m.put("countable", rs.getInt("countable"));
                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);
            System.out.println(list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }
        else if (requestName.equals("OpenSpace_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            // int person_id = (int) dataloaded.get ("person");
            String query = "SELECT count(space_id) as countable2 from space where spacetype_id = 2 and rental_id is NULL";
            PreparedStatement statement = connection.prepareStatement(query);
            //statement.setInt (1,person_id);
            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
                m.put("countable2", rs.getInt("countable2"));
                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);
            System.out.println(list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }

        else if (requestName.equals("Office_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            // int person_id = (int) dataloaded.get ("person");
            String query = "SELECT count(space_id) as countable3 from space where spacetype_id = 3 and rental_id is NULL";
            PreparedStatement statement = connection.prepareStatement(query);
            //statement.setInt (1,person_id);
            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
                m.put("countable3", rs.getInt("countable3"));
                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);
            System.out.println(list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }
        else if (requestName.equals("Number_person")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            //int nombre  = (int) dataloaded.get("NumberP");
            System.out.println(dataLoaded.get("NumberP"));
            //Integer nb = (int ) dataLoaded.get("NumberP");
            String query = "Select space_name,size_space, max_person_number,floor_number, building_name,price from space INNER JOIN floor_ ON space.floor_id = floor_.floor_id INNER JOIN building ON floor_.building_id = building.building_id Where max_person_number >= 1 and rental_id is NULL";
            System.out.println("Requete  : " + query);


           // statement.setInt (1,nombre);

            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();

            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
                System.out.println("INTERIEUUR");
                m.put("space_name", rs.getString("space_name"));
                m.put("size_space", rs.getInt("size_space"));
                m.put("max_person_number", rs.getInt("max_person_number"));
                m.put("floor_number", rs.getInt("floor_number"));
                m.put("building_name", rs.getString("building_name"));
                m.put("price", rs.getInt("price"));

                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);
            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }


        else if (requestName.equals("position")) {
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

        } else if (requestName.equals("equipment_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            List<Map> equipments = new ArrayList<>();
            String sql;
            int request_id = (int)dataLoaded.get("request_id");
            if (request_id==1){
                sql = "select equipment_id, equipment_name, is_sensor from equipment where rental_id = (select rental_id from space where space_id = ?) and equipment_id NOT in (select equipment_id from position_ where equipment_id is not null)";
            }
            else if (request_id ==2){
                sql="select equipment_id, equipment_name, is_sensor from equipment where rental_id = (select rental_id from space where space_id = ?) and equipment_id NOT in (select equipment_id from position_ where equipment_id is not null) and equipmenttype_id!=5";
            }else if (request_id ==3){
                sql = "select equipment_id, equipment_name, is_sensor from equipment where rental_id = (select rental_id from space where space_id = ?) and equipment_id NOT in (select equipment_id from position_ where equipment_id is not null) and equipmenttype_id!=4";
            }else if (request_id ==4){
                sql = "select equipment_id, equipment_name, is_sensor from equipment where rental_id = (select rental_id from space where space_id = ?) and equipment_id NOT in (select equipment_id from position_ where equipment_id is not null) and equipmenttype_id!=4 and equipmenttype_id!=5";
            }else if (request_id ==5){
                sql = "select equipment_id, equipment_name, is_sensor from equipment where rental_id = (select rental_id from space where space_id = ?) and equipment_id NOT in (select equipment_id from position_ where equipment_id is not null) and equipmenttype_id!=9 and equipmenttype_id!=8 and equipmenttype_id!=4";
            } else{
                sql = "select equipment_id, equipment_name, is_sensor from equipment where rental_id = (select rental_id from space where space_id = ?) and equipment_id NOT in (select equipment_id from position_ where equipment_id is not null) and equipmenttype_id!=5 and equipmenttype_id!=9 and equipmenttype_id!=8 and equipmenttype_id!=4";
            }
            PreparedStatement statement = connection.prepareStatement(sql);
            // setInt permits to put value in sql variable.
            statement.setInt(1, (Integer) dataLoaded.get("space_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("equipment_id", rs.getInt("equipment_id"));
                hm.put("equipment_name", rs.getString("equipment_name"));
                hm.put("is_sensor", rs.getBoolean("is_sensor"));
                equipments.add(hm);
            }
            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", equipments);

            String responseMsg = mapper.writeValueAsString(response);

            writer.println(responseMsg);

        } else if (requestName.equals("place_equipment")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            System.out.println("data loaded" + request.getData());
            List<Map> name = new ArrayList<>();
            String updatePosition = "update position_ set equipment_id = ?, available = false where position_id = ?";
            String updateEquipment = "UPDATE equipment set equipment_state = true where equipment_id = ?";
            PreparedStatement statement1 = connection.prepareStatement(updatePosition);
            PreparedStatement statement2 = connection.prepareStatement(updateEquipment);
            statement1.setInt(1, (Integer) dataloaded.get("equipment_id"));
            statement1.setInt(2, (Integer) dataloaded.get("position_id"));
            statement2.setInt(1, (Integer) dataloaded.get("equipment_id"));
            statement1.executeUpdate();
            statement2.executeUpdate();
            System.out.println("nb lines updated");

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("equipment_on_position")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            List<Map> equipment = new ArrayList<>();
            String sql = "SELECT equipment_id, equipment_name, equipment_state from position_ NATURAL JoiN equipment where position_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("position_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("equipment_id", rs.getInt("equipment_id"));
                hm.put("equipment_name", rs.getString("equipment_name"));
                hm.put("equipment_state", rs.getBoolean("equipment_state"));
                equipment.add(hm);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", equipment);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("uninstall_equipment")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            System.out.println("data loaded" + request.getData());
            List<Map> name = new ArrayList<>();
            String updatePosition = "update position_ set equipment_id = null, available = true where position_id = ?";
            String updateEquipment = "UPDATE equipment set equipment_state = false where equipment_id = ?";
            PreparedStatement statement1 = connection.prepareStatement(updatePosition);
            PreparedStatement statement2 = connection.prepareStatement(updateEquipment);
            statement1.setInt(1, (Integer) dataloaded.get("position_id"));
            statement2.setInt(1, (Integer) dataloaded.get("equipment_id"));
            statement1.executeUpdate();
            statement2.executeUpdate();

            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        } else if (requestName.equals("building_access")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            System.out.println("name " + dataLoaded);
            System.out.println("name " + requestName);
            List<Map> name = new ArrayList<>();
            String sql = "select building_id, building_name from person natural join access_card natural join card_building natural join building where card_id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("building_id", rs.getInt("building_id"));
                hm.put("building_name", rs.getString("building_name"));
                name.add(hm);
            }
            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);
            System.out.println (response);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("floor_access")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            System.out.println("name " + dataLoaded);
            System.out.println("name " + requestName);
            List<Map> name = new ArrayList<>();
            String sql = "select floor_id, floor_number from person natural join access_card natural join card_building natural join building natural join floor_ where card_id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("floor_id", rs.getInt("floor_id"));
                hm.put("floor_number", rs.getString("floor_number"));
                name.add(hm);
            }
            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);
            System.out.println (response);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("space_access")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            System.out.println("name " + dataLoaded);
            System.out.println("name " + requestName);
            List<Map> name = new ArrayList<>();
            String sql = "select space_id, space_name from  building natural join floor_ natural join space natural join company where floor_id =? and building_id =? and company_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("floor_id"));
            statement.setInt(2, (Integer) dataLoaded.get("building_id"));
            statement.setInt(3, (Integer) dataLoaded.get("company_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("space_id", rs.getInt("space_id"));
                hm.put("space_name", rs.getString("space_name"));
                name.add(hm);
            }
            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);
            System.out.println (response);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("name_person")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            System.out.println("name " + dataLoaded);
            System.out.println("name " + requestName);
            List<Map> name = new ArrayList<>();
            String sql = "select person_firstname,person_surname,birth_date,position_p, clearance_level from person natural join access_card where card_id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                //hm.put("person_id", rs.getInt("person_id"));
                hm.put("person_surname", rs.getString("person_surname"));
                hm.put("person_firstname", rs.getString("person_firstname"));
                hm.put("birth_date", rs.getString("birth_date"));
                hm.put("position_p", rs.getString("position_p"));
                hm.put("clearance_level", rs.getInt("clearance_level"));
                name.add(hm);
            }
            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);
            System.out.println (response);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("building_person")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            System.out.println("name " + dataLoaded);
            System.out.println("name " + requestName);
            List<Map> name = new ArrayList<>();
            String sql = "select building_name from person natural join access_card natural join building  where card_id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                //hm.put("person_id", rs.getInt("person_id"));
                hm.put("building_name", rs.getString("building_name"));
                name.add(hm);
            }
            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);
            System.out.println (response);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("search_card")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            System.out.println("name " + dataLoaded);
            System.out.println("name " + requestName);
            List<Map> name = new ArrayList<>();
            String sql = "select person_firstname,person_surname,birth_date,position_p, clearance_level from person natural join access_card where card_id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                //hm.put("person_id", rs.getInt("person_id"));
                hm.put("person_surname", rs.getString("person_surname"));
                hm.put("person_firstname", rs.getString("person_firstname"));
                hm.put("birth_date", rs.getString("birth_date"));
                hm.put("position_p", rs.getString("position_p"));
                hm.put("clearance_level", rs.getInt("clearance_level"));
                name.add(hm);
            }
            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);
            System.out.println (response);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if (requestName.equals("tempA")) {
            System.out.println("serveur ok");
            ObjectMapper mapper = new ObjectMapper();
            String sql = "SELECT inside_temperature, outside_temperature, pstore FROM datatemp WHERE id_datatemp = 1";
            String sql2 = "select lum_exterieure,lum_interieure, pteinte from luminosite WHERE id_lum = 1";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            ResultSet rs2 = statement2.executeQuery();

            Map<String, Object> hm = new HashMap<>();
            while (rs.next()) {
                hm.put("tempin", rs.getInt("inside_temperature"));
                hm.put("tempex", rs.getInt("outside_temperature"));
                hm.put("pstore", rs.getInt("pstore"));
            }
            while (rs2.next()) {
                hm.put("lumex", rs2.getInt("lum_exterieure"));
                hm.put("lumin", rs2.getInt("lum_interieure"));
                hm.put("pteinte", rs2.getInt("pteinte"));
            }
           System.out.println(hm);

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", hm);
            System.out.println (response);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);


        }
        else if (requestName.equals("temp")) {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> dataloaded = (Map<String, Object>) request.getData();
            System.out.println(dataloaded );
            int tempex = (int) dataloaded.get("temp_exterieure");
            int tempin = (int) dataloaded.get("temp_interieure");
            int pstore0 = 0;
            int a = Math.max(Math.abs(tempex),Math.abs(tempin));
            int b = Math.min(Math.abs(tempex),Math.abs(tempin));
            int d = (a-b);
            int psore= (d*100/a);
            if(tempex == tempin){
                System.out.println(pstore0);
            }
            if(tempex != tempin) {
                System.out.println(a);
                System.out.println(b);
                System.out.println(d);
                System.out.println(psore);
            }
        String sql = "UPDATE datatemp SET inside_temperature = " + tempin + ", outside_temperature = " + tempex + ", pstore = " + psore + " WHERE id_datatemp = 1";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            System.out.println("Update ok");

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if (requestName.equals("lum")) {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> dataloaded1 = (Map<String, Object>) request.getData();
            System.out.println(dataloaded1 );
            int lumex = (int) dataloaded1 .get("lumminosite_exterieure");
            int limin = (int) dataloaded1 .get("luminosite_interieure");
            int psteinte0 = 0;
            int a = Math.abs(lumex);
            int b = Math.abs(limin);
            int d = (a-b);
            int e = (100*d/a);
            int pteinte =(100 - e);

            if(lumex <15 ){
                System.out.println(psteinte0);
            }
            if(lumex > 15 ) {
                System.out.println(a);
                System.out.println(b);
                System.out.println(d);
                System.out.println(e);
                System.out.println(pteinte);
            }
            String sql = "UPDATE luminosite SET lum_exterieure = " + lumex + ", lum_interieure = " + limin + ", pteinte = " + pteinte + " WHERE id_lum = 1";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            System.out.println("Update lumiere ok");

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("equipment_is_used")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            List<Map> floor = new ArrayList<>();
            String sql = "select equipment_id from equipment where equipment_id not in(select equipment_id from position_ where equipment_id is not null) and equipment_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, (Integer) dataLoaded.get("equipment_id"));
            ResultSet rs = statement.executeQuery();
            boolean isUsed = false;
            while (rs.next()) {
                isUsed = true;
            }
            Map<String, Object> hm = new HashMap<>();
            hm.put("isUsed", isUsed);
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", hm);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);


        }
    }
}
