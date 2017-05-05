package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
    private final String HOST     = "galgedb.cmhy1lnpclzv.eu-central-1.rds.amazonaws.com";
    private final int    PORT     = 3306;
    private final String DATABASE = "galgedb";
    private final String USERNAME = "gruppe11"; 
    private final String PASSWORD = "gruppe11";
		
    private static Connection connection;
    
    public Connector() throws DALException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();
                System.exit(1);
            }
    }
    
    public Connection getConnection(){
    	return connection;
    }
    
    public ResultSet doQuery(String query) throws SQLException{
        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(query);
        return res;
    }
    
    public void doUpdate(String query) throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(query);
    }
}