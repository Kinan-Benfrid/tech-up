package episen.si.ing1.pds.backend.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import episen.si.ing1.pds.backend.server.socket.RequestSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
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
            String sql = "select card_id from access_card WHERE person_id is not null ORDER BY card_id ASC";
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
            String sql = "SELECT person.* FROM person LEFT JOIN access_card ac on person.person_id = ac.person_id where card_id is null AND company_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("company_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("person_id", rs.getInt("person_id"));
                hm.put("person_firstname", rs.getString("person_firstname"));
                hm.put("person_surname", rs.getString("person_surname"));
                hm.put("position_p", rs.getString ("position_p"));
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
            String sql = "select active, card_id, access_card.person_id,position_p, role_id, person_firstname,person_surname from person inner join access_card on person.person_id = access_card.person_id where affected_card=true and company_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("company_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("person_id", rs.getInt("person_id"));
                hm.put("card_id", rs.getInt("card_id"));
                hm.put("person_firstname", rs.getString("person_firstname"));
                hm.put("person_surname", rs.getString("person_surname"));
                hm.put("role_id", rs.getInt("role_id"));
                hm.put("position_p", rs.getString("position_p"));
                hm.put("active", rs.getBoolean("active"));

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
            String sql = "UPDATE access_card set active = false, affected_card = false, person_id =null where card_id=?";
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
        else if (requestName.equals("Insert_New_Company")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            String query = " insert into company (company_name) values (?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, (String) dataLoaded.get("jT1"));

            statement.executeUpdate();

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if (requestName.equals("Insert_New_MDA")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            String query = " insert into maintenance_department_administrators (company_id) Select max(company_id) as max from company";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.executeUpdate();

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if (requestName.equals("Insert_New_Rental")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            String query ="Insert into rental (id_mda) Select max(id_mda) as max from maintenance_department_administrators";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.executeUpdate();

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
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

            String query ="Select space.rental_id,space_id,space_name,size_space, max_person_number,floor_number, building_name,price,space.floor_id from space INNER JOIN floor_ ON space.floor_id = floor_.floor_id INNER JOIN building ON floor_.building_id = building.building_id Where rental_id is NULL and max_person_number >= ? and price between ? and ? and spacetype_id in ( select spacetype_id from space where spacetype_id = ? or spacetype_id = ? or spacetype_id = ?) order by price asc limit ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, (Integer) dataLoaded.get("max_person_number"));
            statement.setInt(2, (Integer) dataLoaded.get("budjetMin"));
            statement.setInt(3, (Integer) dataLoaded.get("budjetMax"));

            statement.setInt(4, (Integer) dataLoaded.get("valeur_space_id_liste_salle_reunion"));
            statement.setInt(5, (Integer) dataLoaded.get("valeur_space_id_liste_open_space"));
            statement.setInt(6, (Integer) dataLoaded.get("valeur_space_id_liste_bureau"));
            statement.setInt(7, (Integer) dataLoaded.get("valeur_liste_total_pour_limit"));


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

        else if (requestName.equals("Delete_Rental")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            String query = "DELETE from rental where rental_id in (Select max(rental_id) from rental)";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.executeUpdate();


            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
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
        else if (requestName.equals("Select_nbr_equipement_dispo")) {
            ObjectMapper mapper = new ObjectMapper();

            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            String query = "select equipmenttype_id, count(equipment_id) from equipment where equipmenttype_id = ? and rental_id is null group by equipmenttype_id";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,(Integer) dataloaded.get("nmbr_equipment_dispo"));

            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
                m.put("equipmenttype_id", rs.getInt("equipmenttype_id"));
                m.put("count", rs.getInt("count"));
                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }


        else if (requestName.equals("Select_equipment")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataloaded = (Map) request.getData();
            logger.info(String.valueOf(dataloaded));
            String query = "select Distinct(designation),equipmenttype_id from equipment_type";
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
                m.put("designation", rs.getString("designation"));
                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }
        else if (requestName.equals("Select_name_equipment")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            String query = "select equipmenttype_id from equipment_type where designation = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,(String) dataLoaded.get("nom_de_lequipement"));

            ResultSet rs = statement.executeQuery();
            List<Map> list = new ArrayList<>();
            while (rs.next()) {
                Map m = new HashMap();
                m.put("equipmenttype_id", rs.getInt("equipmenttype_id"));
                list.add(m);
            }
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", list);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }


        else if (requestName.equals("Update_equipment")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            String query = "UPDATE equipment SET rental_id = ? where equipment_id in (select equipment_id from (select equipment_id from equipment where equipmenttype_id =? and rental_id is null limit ?)as Alias )";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,(Integer) dataLoaded.get("rental_id_space"));
            statement.setInt(2,(Integer) dataLoaded.get("nmbr_equipment_dispo"));
            statement.setInt(3,(Integer) dataLoaded.get("valeur_liste_du_nbr_dequipement"));
            statement.executeUpdate();

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }

        else if (requestName.equals("Select_count_number_equipment")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            String query = "SELECT count(rental_id) from equipment where rental_id = ? and equipmenttype_id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,(int) dataLoaded.get("rental_id_space"));
            statement.setInt(2,(int) dataLoaded.get("nmbr_equipment_dispo"));


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
            String sql = "select person_surname,person_firstname,birth_date,position_p, r.role_id - 1 as clearance_level, r.designation as position_type from person\n" +
                    "    natural join access_card\n" +
                    "    natural join roles r\n" +
                    "where card_id = ?";
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
            String sql = "select person_firstname,person_surname,birth_date,position_p from person natural join access_card where card_id =?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> hm = new HashMap<>();
                hm.put("person_surname", rs.getString("person_surname"));
                hm.put("person_firstname", rs.getString("person_firstname"));
                hm.put("birth_date", rs.getString("birth_date"));
                hm.put("position_p", rs.getString("position_p"));
                name.add(hm);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if(requestName.equals("mapwindow")){

            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();

            String building = (String) dataLoaded.get("building");
            String floor = (String) dataLoaded.get("floor");
            String space = (String) dataLoaded.get("space");

            logger.info(String.valueOf(dataLoaded));
            /*String sql = "select count(*) as nb from equipment inner join position_ ON equipment.equipment_id = position_.equipment_id\n" +
                    "INNER JOIN space sp\n" +
                    "ON sp.space_id = position_.space_id\n" +
                    "INNER JOIn floor_ f \n" +
                    "ON f.floor_id = sp.floor_id\n" +
                    "INNER JOIN building \n" +
                    "ON building.building_id = f.building_id \n" +
                    "WHERE building.building_name ='" + building +"' AND f.floor_number = '" + floor +"' AND sp.space_name = '" + space +"' AND equipment.equipment_name = 'Fenetre electro-chromatique'";

            */
            String sql = "select count(*) as nb from equipment inner join position_ ON equipment.equipment_id = position_.equipment_id\n" +
                    "INNER JOIN space sp\n" +
                    "ON sp.space_id = position_.space_id\n" +
                    "INNER JOIn floor_ f \n" +
                    "ON f.floor_id = sp.floor_id\n" +
                    "INNER JOIN building \n" +
                    "ON building.building_id = f.building_id \n" +
                    "WHERE building.building_name = 'Batiment Condorcet' AND f.floor_number = '3' AND sp.space_name = 'Open Space 1' AND equipment.equipment_name = 'Fenetre electro-chromatique'\n";
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println(sql);
            ResultSet rs = statement.executeQuery();
            String sql2 = "select equipment_name From equipment WHERE equipment_id = 8";
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            ResultSet rs2 = statement2.executeQuery();

            Map<String, Object> hm = new HashMap<>();
            Map<String, Object> hm2 = new HashMap<>();
            while (rs.next()) {
                hm.put("nbwindow", rs.getInt("nb"));
                System.out.println(hm);

            }
            while (rs2.next()) {
                String equipment_name = rs2.getString("equipment_name");
                hm2.put("equipment_name",equipment_name);

                /*int outside_temperature = rs2.getInt("outside_temperature");
                hm2.put("outside_temperature",outside_temperature);*/
                System.out.println(hm2);
            }

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", hm2);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }

        else if (requestName.equals("EtatActuel")) {
            ObjectMapper mapper = new ObjectMapper();

            String sql = "SELECT blind_level_start,blind_percentage_start,blind_level_add, blind_percentage_add, opacity_level_start, opacity_percentage_start,opacity_level_add,opacity_percentage_add FRom CONFIGURATION";
            String sql2 = "Select level_sunlight,outside_temperature from sensor";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            PreparedStatement statement2 = connection.prepareStatement(sql2);
            ResultSet rs2 = statement2.executeQuery();

            Map<String, Object> hm = new HashMap<>();
            while (rs.next()) {
                int blind_level_start = rs.getInt("blind_level_start");
                int blind_percentage_start = rs.getInt("blind_percentage_start");
                int blind_level_add = rs.getInt("blind_level_add");
                int blind_percentage_add= rs.getInt("blind_percentage_add");

                int opacity_level_start= rs.getInt("opacity_level_start");
                int opacity_percentage_start = rs.getInt("opacity_percentage_start");
                int opacity_level_add = rs.getInt("opacity_level_add");
                int opacity_percentage_add = rs.getInt("opacity_percentage_add");



                hm.put("blind_level_start",blind_level_start);
                hm.put("blind_percentage_start", blind_percentage_start);
                hm.put("blind_level_add", blind_level_add);
                hm.put("blind_percentage_add",blind_percentage_add );
                hm.put("opacity_level_start",opacity_level_start);
                hm.put("opacity_percentage_start", opacity_percentage_start );
                hm.put("opacity_level_add",opacity_level_add);
                hm.put("opacity_percentage_add",opacity_percentage_add );



            }
            while (rs2.next()) {
                int level_sunlight = rs2.getInt("level_sunlight");
                hm.put("level_sunlight",level_sunlight);

                int outside_temperature = rs2.getInt("outside_temperature");
                hm.put("outside_temperature",outside_temperature);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", hm);
            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);


        }
        else if (requestName.equals("store")) {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> dataloaded = (Map<String, Object>) request.getData();
            int vtemp_debut = (int) dataloaded.get("valeurtemp_debut");
            int ptemp = (int) dataloaded.get("pourcentagetemp_debut");
            int vtemp_augment = (int) dataloaded.get("valeurtemp_avance");
            int ptemp_augmente = (int) dataloaded.get("pourcentagetemp_avance");

            String sql = "UPDATE configuration SET blind_level_start = " + vtemp_debut+ ", blind_percentage_start = "+ ptemp + ", blind_level_add = " + vtemp_augment + ", blind_percentage_add = " + ptemp_augmente + " WHERE id = 1";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();

            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if (requestName.equals("lum")) {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> dataloaded1 = (Map<String, Object>) request.getData();
            int valeur_debut = (int) dataloaded1 .get("valeur_debut");
            int pourcentage_debut = (int) dataloaded1 .get("pourcentage_debut");
            int valeur_augment = (int) dataloaded1 .get("valeur_avance");
            int pourcentage_augmente = (int) dataloaded1 .get("pourcentage_avance");

            System.out.println(valeur_debut);
            System.out.println(pourcentage_debut);
            System.out.println(valeur_augment);
            System.out.println(pourcentage_augmente );

            String sql = "UPDATE configuration SET opacity_level_start = " + valeur_debut+ ", opacity_percentage_start = "+ pourcentage_debut + ", opacity_level_add = " + valeur_augment + ", opacity_percentage_add = " + pourcentage_augmente + " WHERE id = 1";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();

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

        } else if (requestName.equals("card_update_role")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            String sql = "UPDATE person SET role_id = ?, position_p = ? WHERE person_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("role_id"));
            statement.setString (2, (String) dataLoaded.get("subtitle"));
            statement.setInt(3, (Integer) dataLoaded.get("person_id"));
            statement.executeUpdate();

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if (requestName.equals("access_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            String sql = "SELECT * FROM getCardSpaceAccessibilitiyList(?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            statement.setInt(2, (Integer) dataLoaded.get("building_id"));
            statement.setInt(3, (Integer) dataLoaded.get("company_id"));
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Map<String, Object> hm = new HashMap<> ();
                hm.put ("id", rs.getInt ("id"));
                hm.put("name", rs.getString ("name"));
                hm.put("building_name", rs.getString ("building_name"));
                hm.put("floor_name", rs.getString ("floor_name"));
                hm.put("type", rs.getString ("type"));
                hm.put("accessible", rs.getBoolean("accessible"));

                name.add(hm);
            }
            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }
        else if (requestName.equals("fullaccess_building_update")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            String sql = "SELECT updateAccessToSpace(?, s.space_id, true) FROM building b join floor_ f on b.building_id = f.building_id join space s on f.floor_id = s.floor_id where b.building_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            statement.setInt(2, (Integer) dataLoaded.get("building_id"));
            ResultSet rs = statement.executeQuery ();
            while(rs.next ()) {

            }

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if (requestName.equals("blockaccess_building_update")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            String sql = "SELECT updateAccessToSpace(?, s.space_id, false) FROM building b join floor_ f on b.building_id = f.building_id join space s on f.floor_id = s.floor_id where b.building_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            statement.setInt(2, (Integer) dataLoaded.get("building_id"));
            ResultSet rs = statement.executeQuery ();
            while(rs.next ()) {

            }

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if (requestName.equals("fullaccess_floor_update")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            String sql = "SELECT updateAccessToSpace(?, s.space_id, true) FROM floor_ f join space s on f.floor_id = s.floor_id where f.floor_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            statement.setInt(2, (Integer) dataLoaded.get("floor_id"));
            ResultSet rs = statement.executeQuery ();
            while(rs.next ()) {

            }

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if (requestName.equals("blockaccess_floor_update")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            String sql = "SELECT updateAccessToSpace(?, s.space_id, false) FROM floor_ f join space s on f.floor_id = s.floor_id where f.floor_id = ?;";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            statement.setInt(2, (Integer) dataLoaded.get("floor_id"));
            ResultSet rs = statement.executeQuery ();
            while(rs.next ()) {

            }

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
        else if (requestName.equals("access_space_update")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            String sql = "SELECT * FROM updateAccessToSpace(?, ?, true)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            statement.setInt(2, (Integer) dataLoaded.get("space_id"));
            ResultSet rs = statement.executeQuery ();
            while(rs.next ()) {

            }

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }
        else if (requestName.equals("blockaccess_space_update")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            String sql = "SELECT * FROM updateAccessToSpace(?, ?, false)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            statement.setInt(2, (Integer) dataLoaded.get("space_id"));
            ResultSet rs = statement.executeQuery ();
            while(rs.next ()) {

            }

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }
        else if (requestName.equals("blockaccess_equip_update")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            String sql = "SELECT * FROM updateAccessToEquip(?, ?, false)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            statement.setInt(2, (Integer) dataLoaded.get("equi_id"));
            ResultSet rs = statement.executeQuery ();
            while(rs.next ()) {

            }

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }
        else if (requestName.equals("access_equip_update")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            String sql = "SELECT * FROM updateAccessToEquip(?, ?, true)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            statement.setInt(2, (Integer) dataLoaded.get("equi_id"));
            ResultSet rs = statement.executeQuery ();
            while(rs.next ()) {

            }
            logger.info ("Access to {} has been granted to {}",dataLoaded.get("card_id"), dataLoaded.get("equi_id") );
            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }
        else if (requestName.equals("position_list")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            String sql = "SELECT e.equipment_name, p.position_id FROM position_ p join equipment e on p.equipment_id = e.equipment_id WHERE p.space_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("space_id"));
            ResultSet rs = statement.executeQuery ();
            while(rs.next ()) {
                Map<String, Object> hm = new HashMap<> ();
                hm.put ("equipment_name", rs.getString("equipment_name"));
                hm.put ("position_id", rs.getInt("position_id"));
                name.add(hm);
            }

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        }
        else if (requestName.equals("clear_allAccess")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            String sql = "WITH deleteAccessSpace as ( DELETE FROM card_space WHERE card_id = ? ) DELETE FROM card_position WHERE card_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, (Integer) dataLoaded.get("card_id"));
            statement.setInt(2, (Integer) dataLoaded.get("card_id"));
            statement.executeUpdate ();

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);
        } else if (requestName.equals("test_access")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            if (String.valueOf (dataLoaded.get ("type")).equals ("space"))  {
                String sql = "SELECT * FROM iscardaccessibletospace(?, ?) as access";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, (Integer) dataLoaded.get("card_id"));
                statement.setInt(2, (Integer) dataLoaded.get("space_id"));

                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    Map<String, Object> hm = new HashMap<>();
                    hm.put("access", rs.getBoolean ("access"));

                    name.add(hm);
                }

                Map<String, Object> response = new HashMap<>();
                response.put("request", requestName);
                response.put("data", name);

                String responseMsg = mapper.writeValueAsString(response);
                writer.println(responseMsg);


            } else if (String.valueOf (dataLoaded.get ("type")).equals ("equipment")) {
                String sql = "SELECT * FROM iscardaccessibletoequip(?, ?) as access";
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, (Integer) dataLoaded.get("card_id"));
                statement.setInt(2, (Integer) dataLoaded.get("equip_id"));

                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    Map<String, Object> hm = new HashMap<>();
                    hm.put("access", rs.getBoolean ("access"));

                    name.add(hm);
                }

                Map<String, Object> response = new HashMap<>();
                response.put("request", requestName);
                response.put("data", name);

                String responseMsg = mapper.writeValueAsString(response);
                writer.println(responseMsg);
            }


        }   else if (requestName.equals("date_update")) {
            ObjectMapper mapper = new ObjectMapper();
            Map dataLoaded = (Map) request.getData();
            logger.info(String.valueOf(dataLoaded));
            List<Map> name = new ArrayList<>();
            String date = (String) dataLoaded.get("subtitle");
            java.util.Date utilDate = Date.valueOf (date);
            java.sql.Date sqlDate = java.sql.Date.valueOf (date);
            System.out.println ("date " + java.sql.Date.valueOf (date));
            System.out.println ("sql date" + sqlDate);
            String sql = "UPDATE access_card SET start_date=? WHERE card_id=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            Calendar cal = Calendar.getInstance ();
            cal.clear (Calendar.DATE);
            cal.clear (Calendar.ZONE_OFFSET);
            cal.clear (Calendar.MILLISECOND);
            cal.clear (Calendar.MINUTE);
            cal.clear (Calendar.HOUR);
            statement.setDate(1, sqlDate,cal);
            statement.setInt (2, (Integer)dataLoaded.get("card_id"));
            System.out.println ("prepare statement" + statement);
            System.out.println ("date update");
            System.out.println (sql);
            statement.executeUpdate();
            System.out.println ("date update 2");

            // response is a map of value that is a list of map
            Map<String, Object> response = new HashMap<>();
            response.put("request", requestName);
            response.put("data", name);

            String responseMsg = mapper.writeValueAsString(response);
            writer.println(responseMsg);

        }
    }
}
