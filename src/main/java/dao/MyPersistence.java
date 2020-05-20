package dao;

import javax.persistence.*;
import java.util.List;

public class MyPersistence<T> {
    private T t;


    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("clubweb");
    protected EntityManager em = factory.createEntityManager();

    public MyPersistence(){}

    public <T> T queryByIndex(Class<? extends T> classT,int id){
        return em.find(classT,id);
    }

    public <T> void addT(T t){
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
    }

    public <T> boolean removeT(Class<? extends T> classT, int index){
        T t = this.queryByIndex(classT, index);
        if(t == null){
            return false;
        }
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
        return true;
    }

    public <T> List<T> findAll(Class<T> entityClass){
        String hql = "from " + entityClass.getSimpleName();
        Query query = em.createQuery(hql);
        List<T> tList = (List<T>) query.getResultList();
        return tList;
    }

    public <T> List<T> findByJpaQl(String jpaQl, float n){
        Query query = em.createQuery(jpaQl);
        query.setParameter("n", n);
        List<T> t = (List<T>) query.getResultList();
        return t;
    }
}
