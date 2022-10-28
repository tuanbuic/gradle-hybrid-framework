package ultilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLServerConnUtil {
    public static Connection getSQLServerConnection() {
        String hostName = "localhost";
        String sqlInstanceName = "automationfc";
        String database = "automationfc";
        String userName = "root";
        String password = "admin";
        return getSQLServerConnection(hostName,sqlInstanceName,database,userName,password);
    }
    private static Connection getSQLServerConnection(String hostName, String sqlInstanceName, String database, String userName, String password){
        Connection conn=null;
        try{
            String connectionURL = "jdbc:sqlserver://"+hostName+":1433"+";instance="+sqlInstanceName+";databaseName="+database;
            conn= DriverManager.getConnection(connectionURL, userName,password);
        }catch(Exception e){
            e.printStackTrace();
        }
        return conn;
    }
}
