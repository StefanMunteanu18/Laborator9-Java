import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntMan {
    private EntMan(){}
    private static EntMan instance = null;
    private static EntityManagerFactory factory;
    private static EntityManager em;
    public static EntityManager entMan(){
        factory = Persistence.createEntityManagerFactory("Name");
        em = factory.createEntityManager();
        return em;
    }

    public static EntMan getInstance()
    {
        if (instance == null)
            instance = new EntMan();

        return instance;
    }

    public static void closeEm(){
        em.close();
        factory.close();
    }
}
