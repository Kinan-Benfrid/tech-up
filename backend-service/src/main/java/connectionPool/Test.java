package connectionPool;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
    private Connection c;


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Test t = new Test();
        DataSource ds = new DataSource();
        t.c= ds.receiveConnection();
        System.out.println(ds.getNbConnection());
        Statement st = t.c.createStatement();
        ResultSet rs = st.executeQuery("select * from Etudiant");
        while (rs.next()){
            String firstname = rs.getString(2);
            String lastname = rs.getString(3);
            System.out.println(firstname);
            System.out.println(lastname);
        }
    }
}
