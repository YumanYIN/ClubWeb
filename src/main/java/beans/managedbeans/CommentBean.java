package beans.managedbeans;

import beans.backingbeans.Comment;
import dao.CommentDAO;
import dao.CommentPersistence;

import javax.faces.bean.ManagedBean;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ManagedBean
@RequestScoped
public class CommentBean implements Serializable {

    private Comment comment;

    public CommentBean(){
        comment = new Comment();
    }

    public String addComment(){
        boolean inserted = CommentPersistence.insertComment(comment);
        if (inserted) return "/comment.xhtml";
        return "/comment.xhtml";
    }

    public String changeNbLike(){
        Map<String,String> params = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        int idComment = Integer.parseInt(params.get("idComment"));
        Comment comment = CommentDAO.searchById(idComment);
        assert comment != null;
        comment.addNbLike();
        if(CommentDAO.updateById(comment)){
            return "/comment.xhtml";
        }
        return "/comment.xhtml";
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

}
