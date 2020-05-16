package dao;

import util.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public static boolean validateAccount(String userName, String password){
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

    public static boolean registerUser(String username, String password, String email){
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DataConnect.getConnection();
            String sql = "INSERT INTO `Users` (`username`, `password`, `email`) VALUES (?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);

            // insert a user
            int nbRowsInserted = ps.executeUpdate();
            if (nbRowsInserted >0) {
                System.out.println(nbRowsInserted + "rows inserted !");
            }

        }catch (SQLException ex){
            System.out.println("Login error --> " + ex.getMessage());
        } finally {
            DataConnect.close(conn);
        }
        // if not insert user
        return false;
    }
}