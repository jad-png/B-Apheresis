package integration;

import config.DIContainer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import utils.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class IntegrationTestBase {
    protected static EntityManagerFactory emf;
    protected EntityManager em;

    @BeforeAll
    static void init() {
        emf = JPAUtils.getEntityManagerFactory();
    }

    @BeforeEach
    void setup() {
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    @AfterEach
    void tearDown() {
        if (em != null) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }

    @AfterAll
    static void cleanup() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
