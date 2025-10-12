package utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtils {
    private static final EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("testPU");
        } catch (Exception e) {
            System.err.println("EntityManagerFactory initialization failed: " + e.getMessage());
            throw new ExceptionInInitializerError(e);
        }
    }

    // create EM Using EMF
    public static EntityManager createEM() {
        return emf.createEntityManager();
    }

    // retrieve created EM
    public static EntityManagerFactory getEntityManagerFactory() {
        return emf;
    }

    // close it
    public static void closeEM() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
