package connectionPool;



import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
    private static Connection connect;

    public Test() {
        JDBCConnectionPool j = new JDBCConnectionPool();

        DataSource ds = null;
        try {
            ds = new DataSource();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        connect = DataSource.receiveConnection();
    }
    public void Delete() throws SQLException{
        Statement st = connect.createStatement();
        int n = st.executeUpdate("Insert into Etudiant(firstname, lastname) values ('Titi', 'Toto')");
                System.out.println( n + " added lign");
    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Test t = new Test();
        t.Delete();



    }
}
