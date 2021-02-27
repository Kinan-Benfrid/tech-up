package connectionPool;



import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
    private Connection c;


    public void fonctionSelect() throws SQLException {

        try{
            Statement stmt = null;

            stmt = c.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT * FROM Etudiants ");

            while (true) {

                if (!rs.next()) break;
                {
                    String name = rs.getString("nom");
                    String surname = rs.getString("prenom");
                    int idUser = rs.getInt("id");
                    System.out.println(name );
                    System.out.println(surname);
                    System.out.println(idUser );
                }
                c.closeConnection();
            }
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Test t = new Test();
        JDBCConnectionPool j = new JDBCConnectionPool();
        DataSource ds = new DataSource();
        t.c= ds.receiveConnection();
        t.c.fonctionSelect();
    }
}
