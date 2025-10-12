import config.DIContainer;
import utils.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        EntityManager em = JPAUtils.getEntityManagerFactory().createEntityManager();
        System.out.println("✅ Hibernate is working. EntityManager opened successfully.");
        em.close();
    }
}
