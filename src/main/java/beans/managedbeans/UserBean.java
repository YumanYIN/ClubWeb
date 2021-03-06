package beans.managedbeans;

import beans.backingbeans.User;
import dao.UserPersistence;
import util.SessionUtils;

import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    private User user;
    private UserPersistence userPersistence;

    public UserBean() {
        user = new User();
    }

    public String RegisterAccount() {
        if (user.getUserName() != null && user.getEmail() != null && user.getPassword() != null){
            userPersistence = new UserPersistence();
            boolean registered = userPersistence.registerUser(
                    user.getUserName(),
                    user.getPassword(),
                    user.getEmail());
            if(registered){
                return "login.xhtml?faces-redirect=true";
            }
        }
        return "register.xhtml";
    }

    public String validateUserLogin () {
        userPersistence = new UserPersistence();
        if (user.getUserName() != null && user.getPassword() != null){
            boolean valid = userPersistence.validateAccount(
                    user.getUserName(),
                    user.getPassword()
            );
            if (valid) {
                HttpSession session = SessionUtils.getSession();
                session.setAttribute("username", user.getUserName());
                // keep session 30 minutes
                session.setMaxInactiveInterval(30*60); // unit is second, here means 30 minutes
                return "/index.xhtml?faces-redirect=true";
            }
        }
        return "/login.xhtml";
    }

    public boolean isLoggedIn() {
        HttpSession session = SessionUtils.getSession();
        if (session != null){
            String username = (String) session.getAttribute("username");
            if(username != null){
                return true;
            }
        }
        return false;
    }

    public String logoutUser(){
        HttpSession session = SessionUtils.getSession();
        if (session != null){
            session.invalidate();
        }
        return "/index.xhtml?faces-redirect=true";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



}
