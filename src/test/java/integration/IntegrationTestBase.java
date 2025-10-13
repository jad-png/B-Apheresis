package integration;

import config.DIContainer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import utils.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class IntegrationTestBase {
    protected EntityManagerFactory emf;
    protected EntityManager em;

    @BeforeEach
    void setup () {
        emf = JPAUtils.getEntityManagerFactory();
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @AfterEach
    void tearDown () {
        if (em != null) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }

        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
