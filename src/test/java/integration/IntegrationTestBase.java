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
        em = JPAUtils.createEM();
        em.getTransaction().begin();
    }

    @AfterEach
    void tearDown () {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        em.close();
        emf.close();
    }
}
