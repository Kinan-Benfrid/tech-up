package episen.si.ing1.pds.client.model;

import connectionPool.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Model {
    private Connection c;

    public Model(DataSource ds){
        this.c = ds.receiveConnection();
    }

    public String select(){
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

    public void insert(String firstName, String lastName){
        int nbLinesAdded = 0;
        try {
            Statement statement = c.createStatement();
            nbLinesAdded = statement.executeUpdate("INSERT INTO Etudiant (firstname, lastname) VALUES ('"+firstName+"', '"+lastName+"' )");
            System.out.println("Result of the request : " + nbLinesAdded + " line(s) added");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void delete(String firstName, String lastName){
        int nbLinesDeleted = 0;
        try {
            Statement statement = c.createStatement();
            nbLinesDeleted = statement.executeUpdate("DELETE FROM Etudiant WHERE firstname= '"+firstName+"' and lastname = '"+lastName+"' ");
            System.out.println("Result of the request : " + nbLinesDeleted + " line(s) deleted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void update(String firstName, String lastName, String newFirstName, String newLastName){
        int nbLinesUpdated = 0;
        try {
            Statement statement = c.createStatement();
            nbLinesUpdated = statement.executeUpdate("UPDATE Etudiant SET firstname= '"+newFirstName+"', lastname = '"+newLastName+"' WHERE firstname = '"+firstName+"' and lastname = '"+lastName+"'");
            System.out.println("Result of the request : " + nbLinesUpdated + " line(s) updated");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DataSource ds = new DataSource();
        Model m = new Model(ds);
        m.delete("kinan","Benfrid");
        m.delete("mehdi","koumad");
        m.update("?","?","anas","zakaria");
        System.out.println(m.select());



    }

}
