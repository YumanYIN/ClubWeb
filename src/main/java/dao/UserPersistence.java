package dao;

import beans.backingbeans.User;

public class UserPersistence extends MyPersistence<User> {
    private User user;
    private MyPersistence persistence = new MyPersistence();

    public UserPersistence(){}

    public boolean validateAccount(String userName, String password){
        em.getTransaction().begin();
        //user = em.find(User.class, userName);
        user = em.createQuery(
                "SELECT u from User u WHERE u.userName = :userName", User.class).
                setParameter("userName", userName).getSingleResult();
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

    public void edit(int id, String userName){
        em.getTransaction().begin();
        user = super.queryByIndex(User.class, id);
        user.setUserName(userName);
        em.getTransaction().commit();
    }


}
