package dao.impl;

import config.DIContainer;
import dao.interfaces.DonationDao;
import entity.Donation;
import utils.Loggable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DonationDaoImpl extends Loggable implements DonationDao {
    private final EntityManager em;

    public DonationDaoImpl() {
        EntityManagerFactory emf = DIContainer.getInstance().getBean(EntityManagerFactory.class);
        em = emf.createEntityManager();
    }


    @Override
    public Donation save(Donation donation) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Donation> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Donation> findAll() {
        return Collections.emptyList();
    }

    @Override
    public List<Donation> findByDonationId(Long id) {
        return Collections.emptyList();
    }

    @Override
    public List<Donation> findByRecipientId(Long id) {
        return Collections.emptyList();
    }
}
