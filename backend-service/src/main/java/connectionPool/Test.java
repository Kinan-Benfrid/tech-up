package connectionPool;



import javax.xml.crypto.Data;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
    private Connection c;
    private Statement st;
    private DataSource ds;

    public Test() throws SQLException, ClassNotFoundException {

        ds= new DataSource();
        c=ds.receiveConnection();
        st=c.createStatement();
    }
    public void Insert() throws SQLException { //insert function
        try {
            st = c.createStatement();
            int i = st.executeUpdate("INSERT INTO Etudiant (firstname, lastname) VALUES ('?', '?' )");
            System.out.println("result of the request : " + i + " lines added");
        } catch (SQLException e) {
            System.err.println("error");
            System.err.println(e.getMessage());
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Test t = new Test();
        JDBCConnectionPool j = new JDBCConnectionPool();
        DataSource ds = new DataSource();
        t.c= ds.receiveConnection();
        Statement st = t.c.createStatement();
        ResultSet rs = st.executeQuery("select * from Etudiant"); // sql request for test
        while (rs.next()){
            String firstname = rs.getString(2);
            String lastname = rs.getString(3);
            System.out.println(firstname);
            System.out.println(lastname);
        }
        t.Insert();
    }
}
