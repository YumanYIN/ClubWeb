package dao;

import beans.backingbeans.Comment;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.persistence.PersistenceException;
import java.util.List;

public class CommentPersistence extends MyPersistence<Comment> {
    private Comment comment;
    //private MyPersistence persistence = new MyPersistence();

    public CommentPersistence(){}

    public static List<Comment> getListOfAll(){
        return new MyPersistence<Comment>().findAll(Comment.class);
    }

    public static boolean insertComment(Comment comment) {
        try {
            MyPersistence persistence = new MyPersistence();
            persistence.addT(comment);
            return true;
        }catch (PersistenceException e){
            System.out.println("Insert Comment Error --> " + e.getMessage());
        }
        return false;
    }
}
