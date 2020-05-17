package dao;

import beans.backingbeans.Comment;
import util.DataConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CommentDAO {

    public static List<Comment> getListOfAll(){
        Connection conn = null;
        PreparedStatement ps = null;
        List<Comment> listComment = null;
        UserDAO userDAO = null;
        try {
            conn = DataConnect.getConnection();
            String sql = "SELECT idComment, idUser, text, date, nblike FROM `Comments`";
            assert conn != null;
            ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Comment comment = new Comment();
                comment.setIdComment(rs.getInt("idComment"));
                comment.setDate(rs.getDate("data"));
                comment.setText(rs.getString("text"));
                comment.setNbLike(rs.getInt("nblike"));
                comment.setAuthor(userDAO.getUserById(rs.getInt("idUser")));
                assert listComment != null;
                listComment.add(comment);
                System.out.println(comment.getText());
            }
            return null;

        } catch (SQLException ex){
            System.out.println("CommentDAO getListOfAll Error --> " + ex.getMessage());
        } finally {
            DataConnect.close(conn);
        }
        // if not valid user login
        return null;
    }

}
