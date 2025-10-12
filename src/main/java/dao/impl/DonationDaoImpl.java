package dao.impl;

import config.DIContainer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class DonationDaoImpl {
    private final EntityManager em;

    public DonationDaoImpl() {
        EntityManagerFactory emf = DIContainer.getInstance().getBean(EntityManagerFactory.class);
        em = emf.createEntityManager();
    }

    
}
