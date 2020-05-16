package beans.managedbeans;

import beans.backingbeans.User;
import dao.UserDAO;
import org.w3c.dom.UserDataHandler;
import util.SessionUtils;

import javax.faces.bean.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {

    private User user;

    public UserBean() {
        user = new User();
    }

    public String RegisterAccount() {
        boolean registered = UserDAO.registerUser(
                user.getUserName(),
                user.getPassword(),
                user.getEmail());
        return null;
    }

    public String validateUserLogin () {
        boolean valid = UserDAO.validateAccount(
                user.getUserName(),
                user.getPassword()
        );
        if (valid) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", user.getUserName());
            // keep session 30 minutes
            session.setMaxInactiveInterval(30*60); // unit is second, here means 30 minutes
            return "welcome.xhtml";
        }
        return "login.xhtml";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



}