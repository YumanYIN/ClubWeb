package dao;

import beans.backingbeans.Comment;
import beans.backingbeans.User;
import util.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentDAO {

    public static List<Comment> getListOfAll(){
        Connection conn = null;
        PreparedStatement ps = null;
        List<Comment> listComment = new ArrayList<Comment>();
        try {
            conn = DataConnect.getConnection();
            String sql = "SELECT `idComment`, `firstName`, `lastName`, `email`, `text`, `date`, `nblike` FROM `Comments`";
            assert conn != null;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Comment comment = new Comment();
                comment.setIdComment(rs.getInt("idComment"));
                comment.setDate(rs.getString("date"));
                comment.setText(rs.getString("text"));
                comment.setNbLike(rs.getInt("nblike"));
                comment.setFirstName(rs.getString("firstName"));
                comment.setLastName(rs.getString("lastName"));
                comment.setEmail(rs.getString("email"));
                assert listComment != null;
                listComment.add(comment);
                //System.out.println(comment.getText());
            }
            return listComment;

        } catch (SQLException ex){
            System.out.println("CommentDAO getListOfAll Error --> " + ex.getMessage());
        } finally {
            DataConnect.close(conn);
        }
        // if not valid user login
        return null;
    }

    public static boolean insertComment(String firstName, String lastName, String email, String text, String date){
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DataConnect.getConnection();
            String sql = "INSERT INTO `Comments` (`firstName`,`lastName`, `email`, `text`, `date`) VALUES (?,?,?,?,?)";
            assert conn != null;
            ps = conn.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2,lastName);
            ps.setString(3,email);
            ps.setString(4, text);
            ps.setString(5, date);

            // insert a comment
            int nbRowsInserted = ps.executeUpdate();
            if (nbRowsInserted >0) {
                System.out.println(nbRowsInserted + "rows inserted !");
                return true;
            }

        }catch (SQLException ex){
            System.out.println("Login error --> " + ex.getMessage());
        } finally {
            DataConnect.close(conn);
        }
        return false;
    }

    public static Comment searchById(int idComment){
        Connection conn = null;
        PreparedStatement ps = null;
        Comment comment = null;

        try {
            conn = DataConnect.getConnection();
            String sql = "SELECT * FROM `Comments` WHERE idComment = ?";
            assert conn != null;
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idComment);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                comment = new Comment();
                comment.setNbLike(rs.getInt("nblike"));
                comment.setText(rs.getString("text"));
                comment.setDate(rs.getString("date"));
                comment.setIdComment(idComment);
                comment.setFirstName(rs.getString("firstName"));
                comment.setLastName(rs.getString("lastName"));
                comment.setEmail(rs.getString("email"));
            }
            return comment;

        }catch (SQLException ex){
            System.out.println("Login error --> " + ex.getMessage());
        } finally {
            DataConnect.close(conn);
        }
        return null;
    }

    public static boolean updateById(Comment comment){
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DataConnect.getConnection();
            String sql = "UPDATE `Comments` SET `firstName` = ?, `lastName` = ?, `email` = ?, `text` = ?, `date` = ?, `nblike` = ? WHERE idComment = ?";
            assert conn != null;
            ps = conn.prepareStatement(sql);
            ps.setString(1, comment.getFirstName());
            ps.setString(2, comment.getLastName());
            ps.setString(3, comment.getEmail());
            ps.setString(4, comment.getText());
            ps.setString(5, comment.getDate());
            ps.setInt(6, comment.getNbLike());
            // set idComment (where)
            ps.setInt(7, comment.getIdComment());

            // update a comment
            int nbRowsInserted = ps.executeUpdate();
            if (nbRowsInserted >0) {
                System.out.println(nbRowsInserted + "rows updated !");
                return true;
            }

        }catch (SQLException ex){
            System.out.println("Login error --> " + ex.getMessage());
        } finally {
            DataConnect.close(conn);
        }
        return false;
    }

}
