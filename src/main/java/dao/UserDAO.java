package dao;

import beans.backingbeans.User;
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

    public User getUserById(int idUser){
        Connection conn = null;
        PreparedStatement ps = null;
        User user = new User();
        try {
            conn = DataConnect.getConnection();
            String sql = "SELECT FROM `Users` WHERE userid = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idUser);
            ResultSet rs = ps.executeQuery();

            while (rs.next()){
                user.setUserId(rs.getInt("userid"));
                user.setUserName(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setEmail(rs.getString("email"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setAddress(rs.getString("address"));
                user.setRole(rs.getString("role"));
            }
            return user;

        }catch (SQLException ex){
            System.out.println("Login error --> " + ex.getMessage());
        } finally {
            DataConnect.close(conn);
        }
        // if not insert user
        return null;
    }
}
