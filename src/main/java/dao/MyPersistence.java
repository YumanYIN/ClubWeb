package dao;

import beans.backingbeans.Comment;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public class MyPersistence<T> implements Serializable{
    private T t;

    protected Session SESSION;
    protected Transaction TRANS;

    protected final String className;
    protected final String tableName;
    protected final Class<T> clazz;

    //private EntityManagerFactory factory = Persistence.createEntityManagerFactory("clubweb");
    //protected EntityManager em = factory.createEntityManager();

    public MyPersistence(Class<T> clazz){
        this.clazz = clazz;
        Table tableAnnotation = clazz.getAnnotation(Table.class);
        if (tableAnnotation == null || tableAnnotation.name().isEmpty()) {
            tableName = clazz.getSimpleName().toLowerCase();
        } else {
            tableName = tableAnnotation.name();
        }
        className = clazz.getSimpleName();
        getSession();
    }

    /**
     * Open Session by :
     * 1.Configuration().configure()
     * 2.buildSessionFactory()
     * 3.openSession()
     * @return
     */
    public Session getSession() {
        if (SESSION == null || ! SESSION.isOpen()) {
            SESSION = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory().openSession();
        }
        return SESSION;
    }

    public void closeSession() {
        getSession().close();
    }

    public <T> void addT(T t){
        TRANS = getSession().beginTransaction();
        getSession().save(t);
        TRANS.commit();
        closeSession();
        System.out.println("add done!");
    }

    public <T> void update(T t){
        TRANS = getSession().beginTransaction();
        getSession().update(t);
        TRANS.commit();
        closeSession();
        System.out.println("update done!");
    }

    public void save(T toSave) {
        TRANS = getSession().beginTransaction();
        getSession().save(toSave);
        TRANS.commit();
        closeSession();
        System.out.println("add done!");
    }

    public void remove(T toRemove) {
        TRANS = getSession().beginTransaction();
        getSession().delete(toRemove);
        TRANS.commit();
        closeSession();
        System.out.println("remove done!");
    }

    public List<T> getAll() {
        return getSession().createQuery("FROM " + className, clazz).list();
    }


    public boolean isEmpty() {
        System.out.println("[DEBUG] isEmpty " + tableName);
        return ((BigInteger) getSession().createSQLQuery("SELECT EXISTS (SELECT NULL FROM " + tableName + ")").uniqueResult()).intValue() == 0;
    }



}
