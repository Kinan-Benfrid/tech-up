package connectionPool;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Crud {
    private Connection c;
    private Statement st;
    private final DataSource ds;

    public Crud() throws SQLException, ClassNotFoundException {

        ds= new DataSource();
        c= DataSource.receiveConnection();
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
    public void Select() throws SQLException { // insert function select
        try{
            st = c.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT * FROM Etudiant");
            while (true) {
                if (!rs.next()) break;
                {
                    String name = rs.getString("Firstname");
                    String surname = rs.getString("Lastname");
                    int idUser = rs.getInt("id");
                    System.out.println(idUser );
                    System.out.println(name );
                    System.out.println(surname);
                }
                c.close();
            }
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }


    public void Delete(){
        try {
            int n = st.executeUpdate("Delete from Etudiant where firstname = '?' and lastname = '?' ");
            System.out.println(n + " deleted lign");
        } catch (SQLException e) {
            System.err.println("error");
            System.err.println(e.getMessage());
        }
    }



    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Crud t = new Crud();
        JDBCConnectionPool j = new JDBCConnectionPool();
        DataSource ds = new DataSource();
        t.c= DataSource.receiveConnection();
        Statement st = t.c.createStatement();
        ResultSet rs = st.executeQuery("select * from Etudiant"); // sql request for test
        while (rs.next()){
            String firstname = rs.getString(2);
            String lastname = rs.getString(3);
            System.out.println(firstname);
            System.out.println(lastname);
        }
        t.Insert();
        t.Select();
    }
}
