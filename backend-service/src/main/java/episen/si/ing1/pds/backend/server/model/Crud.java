package episen.si.ing1.pds.backend.server.model;

import episen.si.ing1.pds.backend.server.pool.DataSource;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Crud {
    private final DataSource ds;

    public Crud(DataSource ds) {
        this.ds = ds;
    }

    // This Class permits to execute the CRUD operations (create, read, update, delete)
    // For every method of this class, we recover a connection of the ConnectionPool and
    // once the operation is completed, it is put back into the ConnectionPool
    public String select() {
        Connection c = ds.receiveConnection();
        StringBuilder resultQuery = new StringBuilder();
        Statement statement;
        try {
            statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Student");
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String firstname = resultSet.getString(2);
                String lastname = resultSet.getString(3);
                resultQuery.append("ID : "+id + ", Firstname : " + firstname + ", Lastname : " + lastname + "\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ds.putConnection(c);
        return resultQuery.toString();
    }

    public void insert(String firstName, String lastName) {
        Connection c = ds.receiveConnection();
        int nbLinesAdded = 0;
        try {
            Statement statement = c.createStatement();
            nbLinesAdded = statement.executeUpdate("INSERT INTO Student (firstname, lastname) VALUES ('" + firstName + "', '" + lastName + "' )");
            System.out.println("Result of the request : " + nbLinesAdded + " line(s) added");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ds.putConnection(c);
    }

    public void delete(String firstName, String lastName) {
        Connection c = ds.receiveConnection();
        int nbLinesDeleted = 0;
        try {
            Statement statement = c.createStatement();
            nbLinesDeleted = statement.executeUpdate("DELETE FROM Student WHERE firstname= '" + firstName + "' and lastname = '" + lastName + "' ");
            System.out.println("Result of the request : " + nbLinesDeleted + " line(s) deleted");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ds.putConnection(c);
        c = null;
    }

    public void update(String firstName, String lastName, String newFirstName, String newLastName) {
        Connection c = ds.receiveConnection();
        int nbLinesUpdated = 0;
        try {
            Statement statement = c.createStatement();
            nbLinesUpdated = statement.executeUpdate("UPDATE Student SET firstname= '" + newFirstName + "', lastname = '" + newLastName + "' WHERE firstname = '" + firstName + "' and lastname = '" + lastName + "'");
            System.out.println("Result of the request : " + nbLinesUpdated + " line(s) updated");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        ds.putConnection(c);
        c = null;
    }


}
