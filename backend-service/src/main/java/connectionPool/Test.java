package connectionPool;



import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
    private Connection c;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Test t = new Test();
        JDBCConnectionPool j = new JDBCConnectionPool();
        DataSource ds = new DataSource();
        t.c= ds.receiveConnection();
        Statement st = t.c.createStatement();
        ResultSet rs = st.executeQuery("select * from event");
        while (rs.next()){
            String titre = rs.getString(3);
            System.out.println(titre);
        }

    }
}
