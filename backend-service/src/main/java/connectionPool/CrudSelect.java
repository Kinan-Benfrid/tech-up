package connectionPool;



import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class CrudSelect {
    private Connection c;


    public void functionSelect() throws SQLException {

        try{
            Statement stmt = null;

            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT Firstname FROM Etudiant");

            while (true) {

                if (!rs.next()) break;
                {

                    String name = rs.getString("Firstname");
                    //String surname = rs.getString("Lastname");
                    //int idUser = rs.getInt("id");
                   // System.out.println(idUser );
                    System.out.println(name );
                    //System.out.println(surname);

                }
                c.close();
            }
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        CrudSelect t = new CrudSelect();
        JDBCConnectionPool j = new JDBCConnectionPool();
        DataSource ds = new DataSource();
        t.c= ds.receiveConnection();
        t.functionSelect();
    }
}
