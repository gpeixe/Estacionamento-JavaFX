package Utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnection {
    private  static Connection connection = null;

    public static Connection getConnection(){
        try{
            if(connection == null) {
                Class.forName("org.sqlite.JDBC");
               connection = DriverManager.getConnection("jdbc:sqlite:DB/estacionamentoDB");
            }
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
}
