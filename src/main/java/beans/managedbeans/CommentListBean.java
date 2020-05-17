package beans.managedbeans;

import beans.backingbeans.Comment;
import beans.backingbeans.User;
import dao.CommentDAO;

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

@Named
@RequestScoped
public class CommentListBean implements Serializable {

    private List<Comment> listComment;

    @PostConstruct
    public void init(){
        //listComment = CommentDAO.getListOfAll();
        String txt = "J'aime beaucoup votre club, j'ai participé au trek en Bretagne en mars, je suis très détendu, j'espère y retourner!";
        Date dateNow = new Date();
        //listComment.add(new Comment(100, new User(), txt, dateNow, 3));
        listComment = CommentDAO.getListOfAll();
    }

    public List<Comment> getListComment(){
        return listComment;
    }

}
