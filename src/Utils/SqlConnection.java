package Utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class SqlConnection {

    public static Connection getConnection(){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:DB/estacionamentoDB");
            return conn;
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }
    }
}
