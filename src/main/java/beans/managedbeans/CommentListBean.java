package beans.managedbeans;

import beans.backingbeans.Comment;
import beans.backingbeans.User;
import dao.CommentDAO;

import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

import javax.faces.annotation.ManagedProperty;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean
@RequestScoped
public class CommentListBean implements Serializable {

    private List<Comment> listComment = new ArrayList<Comment>();

    public CommentListBean(){
        this.listComment = CommentDAO.getListOfAll();
    }

    public List<Comment> getListComment(){
        return listComment;
    }

    public void setListComment(List<Comment> listComment) {
        this.listComment = listComment;
    }
}
