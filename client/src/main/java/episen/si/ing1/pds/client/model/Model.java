package episen.si.ing1.pds.client.model;

import connectionPool.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Model {
    private Connection c;

    public String select(Connection c){
      StringBuilder resultQuery = new StringBuilder();
      Statement statement;
      try {
          statement = c.createStatement();
          ResultSet resultSet = statement.executeQuery("SELECT * FROM ETUDIANT");
          while(resultSet.next()){
              String firstname = resultSet.getString(2);
              String lastname = resultSet.getString(3);
              resultQuery.append("Firstname : " + firstname + ", Lastname : "+lastname + "\n");
          }
      } catch (SQLException throwables) {
          throwables.printStackTrace();
      }
      finally {
          try {
              c.close();
          } catch (SQLException throwables) {
              throwables.printStackTrace();
          }
      }
      return resultQuery.toString();
    }

    public void insert(Connection c){

    }

    public void delete(Connection c){

    }

    public void update(Connection c){

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DataSource ds = new DataSource();
        DataSource ds2 = new DataSource();

        System.out.println(ds.getJdbcConnectionPool());
        System.out.println(ds2.getJdbcConnectionPool());
        Connection c = ds.receiveConnection();
        System.out.println(ds.getNbConnection());
        Model m = new Model();
        System.out.println(m.select(c));

    }

}
