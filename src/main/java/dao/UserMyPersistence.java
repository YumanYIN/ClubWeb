package dao;

import beans.backingbeans.User;

public class UserMyPersistence extends MyPersistence<User> {
    private User user;

    public UserMyPersistence(){}

    public Boolean validateAccount(String userName, String password){
        em.getTransaction().begin();
        //user = em.find(User.class, userName);
        user = em.createQuery(
                "SELECT u from User u WHERE u.userName = :userName", User.class).
                setParameter("userName", userName).getSingleResult();
        return user.getPassword().equals(password);
    }

    public void edit(int id, String userName){
        em.getTransaction().begin();
        user = super.queryByIndex(User.class, id);
        user.setUserName(userName);
        em.getTransaction().commit();
    }


}
