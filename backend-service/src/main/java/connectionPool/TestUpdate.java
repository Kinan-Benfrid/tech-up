package connectionPool;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestUpdate {
    
    private Connection c;

    // function update data
    public void updateData(int id_user) throws SQLException{
        Connection c=null;
        try{
            Statement stmt=null;
            stmt =c.createStatement();
            ResultSet rs= stmt.executeQuery("update nom from Etudiant set nom=? prenom=? where id=id_user");

        while(true){
             System.out.println("data has been modify");                    
         }
        
    } catch(Exception e){
        System.out.println(e.getMessage());
    }
}
        public static void main(String[] args) throws SQLException, ClassNotFoundException {
            TestUpdate t = new TestUpdate();
            JDBCConnectionPool j = new JDBCConnectionPool();
            DataSource ds = new DataSource();
            t.c= ds.receiveConnection();
           int id_userse=0;
            t.updateData(id_userse);
            
            }
    }
    


                
            
        

  
    
    

