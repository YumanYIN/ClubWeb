package dao;

import beans.backingbeans.User;

import javax.persistence.NoResultException;

public class UserPersistence extends MyPersistence<User> {
    private User user;
    //private MyPersistence persistence = new MyPersistence(User.class);

    public UserPersistence(){
        super(User.class);
    }

    public boolean validateAccount(String userName, String password) {

        try {
            user = getSession().createQuery( "SELECT u from User u WHERE u.userName = :userName", User.class).
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

    public boolean registerUser(String userName, String password, String email){
        User user = new User();
        // add a new user in DB
        try {
            // verify if the username exist in DB
            if ( getUserByName(userName) == null )
            {
                user.setUserName(userName);
                user.setPassword(password);
                user.setEmail(email);
                save(user);
                return true;
            }
        }catch (Exception e){
            System.out.println("Register error --> " + e.getMessage());
        }
        return false;
    }

    public User getUserByName(String userName){
        User user = new User();
        try {
            user = getSession().createQuery("select u from User u where u.userName = :userName", User.class).
                    setParameter("userName", userName).
                    getSingleResult();
        } catch (NoResultException e){
            return null;
        }
        return user;
    }

}
