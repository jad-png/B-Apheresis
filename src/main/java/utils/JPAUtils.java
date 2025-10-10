package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {
    private static final EntityManagerFactory emf;

    static {
        try {
        emf = Persistence.createEntityManagerFactory("B-ApheresisPU");
        } catch (Exception e) {
            System.err.println("EntityManagerFactory initialization failed: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    public static EntityManager createEM() {
        return emf.createEntityManager();
    }

    
}
