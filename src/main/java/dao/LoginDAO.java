package dao;

import util.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {

    public static boolean validate(String userName, String password){
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DataConnect.getConnection();
            ps = conn.prepareStatement("Select username, password From `Users` Where username = ? AND password = ?");
            ps.setString(1, userName);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                // result found, means valid inputs
                return true;
            }
        } catch (SQLException ex){
            System.out.println("Login error --> " + ex.getMessage());
        } finally {
            DataConnect.close(conn);
        }
        // if not valid user login
        return false;
    }
}
