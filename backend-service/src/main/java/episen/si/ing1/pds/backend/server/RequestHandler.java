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
            logger.info(String.valueOf(dataLoaded));
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
            writer.println(responseMsg);
        }

        else if (requestName.equals("floor_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
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
            logger.info(String.valueOf(dataLoaded));
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
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", spaces);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("card_list")) {
            ObjectMapper mapper = new ObjectMapper();
            List<Map> card = new ArrayList<>();
            String sql = "select card_id from access_card where affected_card=false ORDER BY card_id ASC;";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("card_id", rs.getInt("card_id"));
                card.add(hm);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", card);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("card_lost")) {
            ObjectMapper mapper = new ObjectMapper();
            List<Map> card = new ArrayList<>();
            String sql = "select card_id from access_card ORDER BY card_id ASC";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("card_id", rs.getInt("card_id"));
                card.add(hm);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", card);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("name_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
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
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("affected_card")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            List<Map> name = new ArrayList<>();
            String sql = "UPDATE access_card set affected_card = true, person_id =? where card_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataloaded.get("person_id"));
            statement.setInt(2, (Integer) dataloaded.get("card_id"));
            statement.executeUpdate();

            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        } else if (requestName.equals("maj_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
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

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("company_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
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

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("clearance_level0")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            List<Map> name = new ArrayList<>();
            String sql = "UPDATE access_card set clearance_level=0,affected_card = true,person_id =? where card_id=?";
            String sql2 ="insert into card_spacetype (card_id,spacetype_id) values (?,1)";
            PreparedStatement statement1 = connection.prepareStatement(sql);
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement1.setInt(1, (Integer) dataloaded.get("person_id"));
            statement1.setInt(2, (Integer) dataloaded.get("card_id"));
            statement2.setInt(1, (Integer) dataloaded.get("card_id"));
            statement1.executeUpdate();
            statement2.executeUpdate ();
            for (int i = 0; i<11; i++){
                if ( i==3 || i==6 || i==7 || i==10){
                    String sql3 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,"+i+")";
                    PreparedStatement statement3 = connection.prepareStatement(sql3);
                    statement3.setInt(1, (Integer) dataloaded.get("card_id"));
                    statement3.executeUpdate ();
                }
            }

            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("clearance_level1")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            List<Map> name = new ArrayList<>();
            String sql1 = "UPDATE access_card set clearance_level=1,affected_card = true, person_id =? where card_id=?";
            String sql2 ="insert into card_spacetype (card_id,spacetype_id) values (?,1)";
            String sql3 ="insert into card_spacetype (card_id,spacetype_id) values (?,2)";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            PreparedStatement statement3 = connection.prepareStatement(sql3);
            statement1.setInt(1, (Integer) dataloaded.get("person_id"));
            statement1.setInt(2, (Integer) dataloaded.get("card_id"));
            statement2.setInt(1, (Integer) dataloaded.get("card_id"));
            statement3.setInt(1, (Integer) dataloaded.get("card_id"));
            statement1.executeUpdate();
            statement2.executeUpdate ();
            statement3.executeUpdate ();

            for (int i = 0; i<11; i++){
                if (i==2 || i==3 || i==4 || i==6 || i==7 || i==10){
                    String sql4 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,"+i+")";
                    PreparedStatement statement4 = connection.prepareStatement(sql4);
                    statement4.setInt(1, (Integer) dataloaded.get("card_id"));
                    statement4.executeUpdate();

                }

            }

            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("clearance_level2")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            List<Map> name = new ArrayList<>();
            String sql1 = "UPDATE access_card set clearance_level=2,affected_card = true, person_id =? where card_id=?";
            String sql2 ="insert into card_spacetype (card_id,spacetype_id) values (?,1)";
            String sql3 ="insert into card_spacetype (card_id,spacetype_id) values (?,3)";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            PreparedStatement statement3 = connection.prepareStatement(sql3);
            statement1.setInt(1, (Integer) dataloaded.get("person_id"));
            statement1.setInt(2, (Integer) dataloaded.get("card_id"));
            statement2.setInt(1, (Integer) dataloaded.get("card_id"));
            statement3.setInt(1, (Integer) dataloaded.get("card_id"));
            statement1.executeUpdate();
            statement2.executeUpdate ();
            statement3.executeUpdate ();

            for (int i = 0; i<11; i++){
                if (i==1 || i==2 || i==3 || i==4 || i==6 || i==7 || i==8 || i==10){
                    String sql4 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,"+i+")";
                    PreparedStatement statement4 = connection.prepareStatement(sql4);
                    statement4.setInt(1, (Integer) dataloaded.get("card_id"));
                    statement4.executeUpdate();

                }
            }

            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("clearance_level3")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            List<Map> name = new ArrayList<>();
            String sql1 = "UPDATE access_card set clearance_level=3,affected_card = true, person_id =? where card_id=?";
            String sql2 ="insert into card_spacetype (card_id,spacetype_id) values (?,1)";
            String sql3 ="insert into card_spacetype (card_id,spacetype_id) values (?,3)";
            String sql4 ="insert into card_spacetype (card_id,spacetype_id) values (?,2)";
            PreparedStatement statement1 = connection.prepareStatement(sql1);
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            PreparedStatement statement3 = connection.prepareStatement(sql3);
            PreparedStatement statement4 = connection.prepareStatement(sql4);

            statement1.setInt(1, (Integer) dataloaded.get("person_id"));
            statement1.setInt(2, (Integer) dataloaded.get("card_id"));
            statement2.setInt(1, (Integer) dataloaded.get("card_id"));
            statement3.setInt(1, (Integer) dataloaded.get("card_id"));
            statement4.setInt(1, (Integer) dataloaded.get("card_id"));
            statement1.executeUpdate();
            statement2.executeUpdate ();
            statement3.executeUpdate ();
            statement4.executeUpdate();
            for (int i = 0; i<11; i++){
                if (i==1 || i==2 || i==3 || i==4 || i==6 || i==7 || i==8 || i==10){
                    String sql5 = "insert into card_equipmenttype(card_id,equipmenttype_id) values (?,"+i+")";
                    PreparedStatement statement5 = connection.prepareStatement(sql5);
                    statement5.setInt(1, (Integer) dataloaded.get("card_id"));
                    statement5.executeUpdate();

                }
            }

            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("active_cardT")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            List<Map> name = new ArrayList<>();
            String sql = "UPDATE access_card set active = true, affected_card = true, person_id =? where card_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataloaded.get("person_id"));
            statement.setInt(2, (Integer) dataloaded.get("card_id"));
            statement.executeUpdate();

            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        } else if (requestName.equals("active_cardF")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            List<Map> name = new ArrayList<>();
            String sql = "UPDATE access_card set active = false, affected_card = true, person_id =? where card_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataloaded.get("person_id"));
            statement.setInt(2, (Integer) dataloaded.get("card_id"));
            statement.executeUpdate();

            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        } else if (requestName.equals("dissociate")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            List<Map> name = new ArrayList<>();
            String sql = "UPDATE access_card set active = false, affected_card = false,clearance_level=null, person_id =null where card_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataloaded.get("card_id"));
            statement.executeUpdate();

            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("spaceA_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            String query = "Select space_name,size_space, max_person_number,floor_number, building_name,price from space INNER JOIN floor_ ON space.floor_id = floor_.floor_id INNER JOIN building ON floor_.building_id = building.building_id  Where rental_id is NULL";
            PreparedStatement statement = connection.prepareStatement(query);
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

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }

        else if (requestName.equals("meeting_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            String query = "SELECT spacetype_id, count(space_id)as countable from space where spacetype_id = 1 and rental_id is NULL group by spacetype_id";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
              //  m.put("space_reunion", rs.getString("space_name"));
                m.put("spacetype_id", rs.getInt("spacetype_id"));
                m.put("countable", rs.getInt("countable"));
                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }
        else if (requestName.equals("OpenSpace_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            String query = "SELECT spacetype_id, count(space_id)as countable2 from space where spacetype_id = 2 and rental_id is NULL group by spacetype_id";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
               // m.put("space_openSpace", rs.getString("space_name"));
                m.put("spacetype_id", rs.getInt("spacetype_id"));
                m.put("countable2", rs.getInt("countable2"));
                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }

        else if (requestName.equals("Office_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            String query = "SELECT spacetype_id, count(space_id)as countable3 from space where spacetype_id = 3 and rental_id is NULL group by spacetype_id";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
               // m.put("space_office", rs.getString("space_name"));

                m.put("spacetype_id", rs.getInt("spacetype_id"));
                m.put("countable3", rs.getInt("countable3"));
                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }
        else if (requestName.equals("Number_person")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> list = new ArrayList<>();




            String query ="Select space.rental_id,space_id,space_name,size_space, max_person_number,floor_number, building_name,price,space.floor_id from space INNER JOIN floor_ ON space.floor_id = floor_.floor_id INNER JOIN building ON floor_.building_id = building.building_id Where rental_id is NULL and max_person_number >= ? and price between ? and ? and spacetype_id in ( select spacetype_id from space where spacetype_id = ? or spacetype_id = ? or spacetype_id = ?) limit ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, (Integer) dataLoaded.get("max_person_number"));
            statement.setInt(2, (Integer) dataLoaded.get("budjetMin"));
            statement.setInt(3, (Integer) dataLoaded.get("budjetMax"));

            statement.setInt(4, (Integer) dataLoaded.get("valeur_space_id_liste_salle_reunion"));
            statement.setInt(5, (Integer) dataLoaded.get("valeur_space_id_liste_open_space"));
            statement.setInt(6, (Integer) dataLoaded.get("valeur_space_id_liste_bureau"));
            statement.setInt(7, (Integer) dataLoaded.get("valeur_liste_total_pour_limit"));

            /*statement.setString(8,"valeur_space_name_liste_salle_reunion");
            statement.setString(9,"valeur_space_name_liste_open_space");
            statement.setString(10,"valeur_space_name_liste_bureau");
            and space_name in (select space_name from space where space_name = 'Bureau individuel 1' or space_name = 'Open Space 1' or space_name ='Salle de reunion 1')

             */


            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map m = new HashMap();
                m.put("space_id", rs.getInt("space_id"));
                m.put("space_name", rs.getString("space_name"));
                m.put("size_space", rs.getInt("size_space"));
                m.put("max_person_number", rs.getInt("max_person_number"));
                m.put("floor_number", rs.getInt("floor_number"));
                m.put("building_name", rs.getString("building_name"));
                m.put("price", rs.getInt("price"));
                m.put("floor_id", rs.getInt("floor_id"));


                list.add(m);
            }

          System.out.println(dataLoaded.get("valeur_liste_total_pour_limit"));

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);
            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }
       else if (requestName.equals("Insert_Rental")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
             int company_id  = (int) dataLoaded.get("company_id");
            String query = " insert into rental (id_mda) SELECT Distinct (rental.id_mda)  from rental INNER JOIN maintenance_department_administrators  on rental.id_mda = maintenance_department_administrators.id_mda where company_id = " + company_id + " ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.executeUpdate();

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }


        else if (requestName.equals("Select_rental")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> list = new ArrayList<>();
            String query = "Select max(rental_id) as max from rental";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs =  statement.executeQuery();

            while (rs.next()) {
                Map m = new HashMap();
                m.put("rental_id", rs.getInt("max"));
                list.add(m);
            }


            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);
            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }


        else if (requestName.equals("Update_Rental")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            String query = "UPDATE space set rental_id = ? where rental_id is null and space_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,(Integer) dataLoaded.get("rental_id_space"));
            statement.setInt(2,(Integer) dataLoaded.get("id_space"));

            statement.executeUpdate();

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }


        else if (requestName.equals("Imprimante")) {
            ObjectMapper mapper = new ObjectMapper();

            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            String query = "select count(equipment_id) from equipment where equipmenttype_id = 2 and equipment_id NOT in (select equipment_id from attribuate)";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
                m.put("count", rs.getInt("count"));
                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }
        else if (requestName.equals("Prise connectee")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            String query = "select count(equipment_id) from equipment where equipmenttype_id = 1 and equipment_id NOT in (select equipment_id from attribuate)";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
                m.put("count", rs.getInt("count"));
                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }

        else if (requestName.equals("Capteur presence")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            String query = "select count(equipment_id) from equipment where equipmenttype_id = 6 and equipment_id NOT in (select equipment_id from attribuate)";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
                m.put("count", rs.getInt("count"));
                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }

        else if (requestName.equals("Imprimante 3D")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            String query = "select count(equipment_id) from equipment where equipmenttype_id = 9 and equipment_id NOT in (select equipment_id from attribuate)";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
                m.put("count", rs.getInt("count"));
                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }

        else if (requestName.equals("Capteur de ventilation")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            String query = "select count(equipment_id) from equipment where equipmenttype_id = 7 and equipment_id NOT in (select equipment_id from attribuate)";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
                m.put("count", rs.getInt("count"));
                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }

        else if (requestName.equals("Capteur Luminosite")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            String query = "select count(equipment_id) from equipment where equipmenttype_id = 3 and equipment_id NOT in (select equipment_id from attribuate)";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
                m.put("count", rs.getInt("count"));
                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }

        else if (requestName.equals("Television")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            String query = "select count(equipment_id) from equipment where equipmenttype_id = 8 and equipment_id NOT in (select equipment_id from attribuate)";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
                m.put("count", rs.getInt("count"));
                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }

        else if (requestName.equals("Fenetre electro-chromatique")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            String query = "select count(equipment_id) from equipment where equipmenttype_id = 5 and equipment_id NOT in (select equipment_id from attribuate)";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
                m.put("count", rs.getInt("count"));
                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }

        else if (requestName.equals("Capteur Fumee")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            String query = "select count(equipment_id) from equipment where equipmenttype_id = 10 and equipment_id NOT in (select equipment_id from attribuate)";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
                m.put("count", rs.getInt("count"));
                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if (requestName.equals("Video projecteur")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            String query = "select count(equipment_id) from equipment where equipmenttype_id = 4 and equipment_id NOT in (select equipment_id from attribuate)";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
                m.put("count", rs.getInt("count"));
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
            logger.info(String.valueOf(dataLoaded));
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
            logger.info(String.valueOf(dataLoaded));
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
            statement.setInt(1, (Integer) dataLoaded.get("space_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("equipment_id", rs.getInt("equipment_id"));
                hm.put("equipment_name", rs.getString("equipment_name"));
                hm.put("is_sensor", rs.getBoolean("is_sensor"));
                equipments.add(hm);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", equipments);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("place_equipment")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
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

            Map<String, Object> response = new HashMap<>();
            name.add(new HashMap());
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("equipment_on_position")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
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
            logger.info(String.valueOf(dataloaded));
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
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            String sql = "insert into card_building (card_id,building_id) values (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            statement.setInt(2, (Integer) dataLoaded.get("building_id"));
            statement.executeUpdate();

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("floor_access")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            String sql = "insert into card_floor (card_id,floor_id) values (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            statement.setInt(2, (Integer) dataLoaded.get("floor_id"));
            statement.executeUpdate ();

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);
            System.out.println (response);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("space_access")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            String sql = "insert into card_space (card_id,space_id) values (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            statement.setInt(2, (Integer) dataLoaded.get("space_id"));
            statement.executeUpdate ();
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);
            System.out.println (response);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("name_person")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            String sql = "select person_firstname,person_surname,birth_date,position_p, clearance_level,position_type from person natural join access_card where card_id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("person_surname", rs.getString("person_surname"));
                hm.put("person_firstname", rs.getString("person_firstname"));
                hm.put("birth_date", rs.getString("birth_date"));
                hm.put("position_p", rs.getString("position_p"));
                hm.put("clearance_level", rs.getInt("clearance_level"));
                hm.put("position_type", rs.getString("position_type"));
                name.add(hm);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("building_person")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            String sql = "select building_name from person natural join access_card natural join building  where card_id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("building_name", rs.getString("building_name"));
                name.add(hm);
            }
            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("search_card")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            String sql = "select person_firstname,person_surname,birth_date,position_p, clearance_level from person natural join access_card where card_id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("person_surname", rs.getString("person_surname"));
                hm.put("person_firstname", rs.getString("person_firstname"));
                hm.put("birth_date", rs.getString("birth_date"));
                hm.put("position_p", rs.getString("position_p"));
                hm.put("clearance_level", rs.getInt("clearance_level"));
                name.add(hm);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if (requestName.equals("EtatActuel")) {
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
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", hm);
            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if (requestName.equals("temperature")) {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> dataloaded = (Map<String, Object>) request.getData();
            int tempex = (int) dataloaded.get("temp_exterieure");
            int tempin = (int) dataloaded.get("temp_interieure");
            int a = Math.max(Math.abs(tempex),Math.abs(tempin));
            int b = Math.min(Math.abs(tempex),Math.abs(tempin));
            int d = (a-b);
            int psore= (d*100/a); // calculate the percent of blinds
            String sql = "UPDATE datatemp SET inside_temperature = " + tempin + ", outside_temperature = " + tempex + ", pstore = " + psore + " WHERE id_datatemp = 1";
            String sql2 = "INSERT INTO setting_temp (date_inser, inside_temperature, outside_temperature )  VALUES ( NOW(),?, ? )";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setInt(1, (Integer) dataloaded.get("temp_interieure"));
            statement2.setInt(2, (Integer) dataloaded.get("temp_exterieure"));
            statement2.executeUpdate();

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if (requestName.equals("lum")) {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> dataloaded1 = (Map<String, Object>) request.getData();
            int lumex = (int) dataloaded1 .get("lumminosite_exterieure");
            int limin = (int) dataloaded1 .get("luminosite_interieure");
            int a = Math.abs(lumex);
            int b = Math.abs(limin);
            int d = (Math.max(a,b)-Math.min(a,b));
            int e = (100*d);
            int f = (e/Math.max(a,b));
            int pteinte =(f);
            if(lumex < 15 && (0 < limin && limin < 15)){
            }
            if(lumex > 15 && (0 < limin && limin < 15)) {
            }
            String sql = "UPDATE luminosite SET lum_exterieure = " + lumex + ", lum_interieure = " + limin + ", pteinte = " + pteinte + " WHERE id_lum = 1";
            String sql2 = "INSERT INTO setting_bright (date_insert, inside_brigh, outside_brigh )  VALUES ( NOW(),?, ? )";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement2.setInt(1, (Integer) dataloaded1.get("luminosite_interieure"));
            statement2.setInt(2, (Integer) dataloaded1.get("lumminosite_exterieure"));
            statement2.executeUpdate();

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("equipment_is_used")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
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


        } else if (requestName.equals("show_equipment")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> equipment = new ArrayList<>();
            String sql = "select designation from card_equipmenttype natural join equipment_type  where card_id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("designation", rs.getString("designation"));
                equipment.add(hm);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", equipment);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("show_space")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> equipment = new ArrayList<>();
            String sql = "select designation from card_spacetype natural join space_type  where card_id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("designation", rs.getString("designation"));
                equipment.add(hm);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", equipment);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        } else if (requestName.equals("equipment_is_uninstalled")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> floor = new ArrayList<>();
            String sql = "select equipment_id from position_ where equipment_id is not null and position_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("position_id"));
            ResultSet rs = statement.executeQuery();
            boolean isUninstalled = true;
            while (rs.next()) {
                isUninstalled = false;
            }
            Map<String, Object> hm = new HashMap<>();
            hm.put("isUninstalled", isUninstalled);
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", hm);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
    }
}
