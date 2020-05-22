package dao;

import beans.backingbeans.User;
import org.hibernate.HibernateException;

import javax.persistence.NoResultException;

public class UserPersistence extends MyPersistence<User> {
    private User user;
    private MyPersistence persistence = new MyPersistence();

    public UserPersistence(){}

    public boolean validateAccount(String userName, String password){
        em.getTransaction().begin();
        //user = em.find(User.class, userName);
        try {
            user = em.createQuery(
                    "SELECT u from User u WHERE u.userName = :userName", User.class).
                    setParameter("userName", userName).
                    getSingleResult();
        }catch (NoResultException nre){
            // Ignore this because as per the logic this is ok!
        }
        if (user == null){
            return false;
        }
        return user.getPassword().equals(password);
    }

    public boolean registerUser(String username, String password, String email){
        User user = new User();
        user.setUserName(username);
        user.setPassword(password);
        user.setEmail(email);
        // add a new user in DB
        try {
            persistence.addT(user);
            return true;
        }catch (Exception e){
            System.out.println("Register error --> " + e.getMessage());
        }
        return false;
    }

    public void editUserName(int id, String userName){
        em.getTransaction().begin();
        user = super.queryByIndex(User.class, id);
        // modify the userName
        user.setUserName(userName);
        em.getTransaction().commit();
    }


}
