/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bcwellnesdesktop;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author marku
 */
public class DBConnection {
    private static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    private static final String JDBC_URL = "jdbc:derby://localhost:1527/WelllnessDB";
    
    Connection con;
    
    public DBConnection(){
        
    }
    public void connect() throws ClassNotFoundException{
        try{
            Class.forName(DRIVER);
            this.con = DriverManager.getConnection(JDBC_URL,"admin1","1101");
            if(this.con != null){
                System.out.println("Connected to the database!");
            }
        }catch(SQLException ex){
            ex.printStackTrace();
        }
    }
       
}
   
   

