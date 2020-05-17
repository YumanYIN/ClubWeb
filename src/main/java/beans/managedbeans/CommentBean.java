package beans.managedbeans;

import beans.backingbeans.Comment;
import beans.backingbeans.User;
import dao.CommentDAO;

import javax.inject.Named;
import java.util.List;

@Named
public class CommentBean {
    private User user;
    private Comment comment;
    private List<Comment> listComment;
    private CommentDAO commentDAO;

    public CommentBean(){
        user = new User();
        comment = new Comment();
    }

    public List<Comment> ListOfAll(){

        return listComment;
    }
}
