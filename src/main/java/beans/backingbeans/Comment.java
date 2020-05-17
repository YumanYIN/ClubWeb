package beans.backingbeans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Comment {
    private int idComment;
    private String firstName;
    private String lastName;
    private String email;
    private String text;
    private String date;
    private int nbLike; // number of likes

    public Comment(){
        Date dateNow = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();// 格式化时间
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        this.date = sdf.format(dateNow);

        nbLike = 0;
    }

    public Comment(int idComment, String firstName, String lastName, String email, String text, String date, int nbLike){
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEmail(email);
        this.setNbLike(nbLike);
        this.setText(text);
        this.setDate(date);
        this.setIdComment(idComment);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNbLike() {
        return nbLike;
    }

    public void setNbLike(int nbLike) {
        this.nbLike = nbLike;
    }
}
