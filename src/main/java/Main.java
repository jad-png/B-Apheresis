import config.DIContainer;
import utils.JPAUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        JPAUtils.getEntityManagerFactory().close(); // Trigger Hibernate and shut down
    }
}
