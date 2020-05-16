package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DataConnect {

    public static Connection getConnection(){
        String dbURL = "jdbc:mysql://localhost:3306/clubweb?characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
        String dbUser = "root";
        String dbPwd = "password";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(dbURL, dbUser, dbPwd);
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
