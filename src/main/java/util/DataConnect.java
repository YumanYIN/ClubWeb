package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnect {

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/clubweb?characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC",
                    "root",
                    "password"
            );
            return conn;
        } catch (Exception e){
            System.out.println("DataConnect.getConnection() Error --> "+
                    e.getMessage());
            return null;
        }
    }

    public static void close(Connection conn){
        try {
            conn.close();
        } catch (Exception e){
        }
    }
}
