import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
import beans.backingbeans.User;

public class TestJPA {

    public static void main(String[] args) throws Exception{
        EntityManagerFactory entityManagerFactory = null;
        EntityManager entityManager = null;
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("clubweb");
            entityManager = entityManagerFactory.createEntityManager();

            System.out.println( "- Lecture de tous les utilisateurs -----------" );
            List<User> Users = entityManager.createQuery("from User", User.class).getResultList();

            System.out.println( "- Insertion d'un nouvel utilisateur ----------" );
            EntityTransaction trans = entityManager.getTransaction();
            trans.begin();

            User newUser = new User("User","pass","email2","fn","ln","add","roe");
            entityManager.persist( newUser );

            System.out.println( "- Modification d'un utilisateur --------------" );
            newUser.setUserName("userNewName3");
            entityManager.persist(newUser);

            //System.out.println( "- Suppression d'un utilisateur ---------------" );
            entityManager.remove( newUser );

            trans.commit();

        }finally {
            if ( entityManager != null ) entityManager.close();
            if ( entityManagerFactory != null ) entityManagerFactory.close();
        }
    }
}
