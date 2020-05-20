package dao;

import beans.backingbeans.Comment;

import java.util.List;

public class CommentPersistence extends MyPersistence<Comment> {
    private Comment comment;

    public CommentPersistence(){}

    public static List<Comment> getListOfAll(){
        return new MyPersistence<Comment>().findAll(Comment.class);
    }
}
