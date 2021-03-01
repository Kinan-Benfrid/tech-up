package episen.si.ing1.pds.client.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Model {
    private Connection c;

    public String select(Connection c){
      StringBuilder result = new StringBuilder();
      Statement statement;
        try {
            statement = c.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return "";
    }

    public void insert(Connection c){

    }

    public void delete(Connection c){

    }

    public void update(Connection c){

    }
}
