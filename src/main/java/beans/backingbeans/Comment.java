package beans.backingbeans;

import java.util.Date;

public class Comment {
    private int idComment;
    private User author;
    private String text;
    private Date date ;
    private int nbLike = 0; // number of likes

    public Comment(){

    }

    public Comment(int idComment, User user, String text, Date date, int nbLike){
        this.setAuthor(user);
        this.setNbLike(nbLike);
        this.setText(text);
        this.setDate(date);
        this.setIdComment(idComment);
    }

    public int getIdComment() {
        return idComment;
    }

    public void setIdComment(int idComment) {
        this.idComment = idComment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNbLike() {
        return nbLike;
    }

    public void setNbLike(int nbLike) {
        this.nbLike = nbLike;
    }
}
