package dao.impl;

import config.DIContainer;
import dao.interfaces.RecipientDao;
import utils.Loggable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class RecipientDaoImpl extends Loggable implements RecipientDao {
    private final EntityManager em;

    public RecipientDaoImpl() {
        EntityManagerFactory emf = DIContainer.getInstance().getBean(EntityManagerFactory.class);
        this.em = emf.createEntityManager();
    }
}
