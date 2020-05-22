package beans.managedbeans;

import beans.backingbeans.Comment;
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
    private CommentPersistence commentPersistence;

    public CommentBean(){
        comment = new Comment();
    }

    public String addComment(){
        commentPersistence = new CommentPersistence();
        boolean inserted = commentPersistence.insertComment(comment);
        if (inserted) return "comment.xhtml?faces-redirect=true";
        return "comment.xhtml";
    }

    public String changeNbLike(){
        // get param from FacesContext
        Map<String,String> params = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestParameterMap();
        int idComment = Integer.parseInt(params.get("idComment"));
        // change and save
        commentPersistence = new CommentPersistence();
        Comment comment = commentPersistence.searchById(idComment);
        assert comment != null;
        int nb = comment.getNbLike();
        comment.setNbLike(nb + 1);
        commentPersistence.save(comment);
        return "/comment.xhtml?faces-redirect=true";

    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

}
