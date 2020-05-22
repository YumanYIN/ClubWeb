package dao;

import beans.backingbeans.Comment;

import javax.persistence.NoResultException;
import javax.persistence.PersistenceException;
import java.util.List;

public class CommentPersistence extends MyPersistence<Comment> {
    protected Comment comment;
    //private MyPersistence persistence = new MyPersistence();

    public CommentPersistence(){
        super(Comment.class);
    }

    public static List<Comment> getListOfAll(){
        return new MyPersistence<Comment>(Comment.class).getAll();
    }

    public boolean insertComment(Comment comment) {
        try {
            MyPersistence persistence = new MyPersistence(Comment.class);
            persistence.addT(comment);
            return true;
        }catch (PersistenceException e){
            System.out.println("Insert Comment Error --> " + e.getMessage());
        }
        return false;
    }

    public Comment searchById(int idComment){
        try {
            return getSession().createQuery("FROM Comment c where c.idComment = :idComment", Comment.class).
                    setParameter("idComment", idComment).
                    getSingleResult();
        }catch (NoResultException e){
            System.out.println("Search Comment Error --> " + e.getMessage());
        }
        return null;
    }
}
