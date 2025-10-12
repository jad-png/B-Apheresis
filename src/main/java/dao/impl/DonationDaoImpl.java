package dao.impl;

import config.DIContainer;
import dao.interfaces.DonationDao;
import entity.Donation;
import utils.Loggable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
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
        logMethodEntry("save", donation);
        try {
            em.getTransaction().begin();
            Donation saved = em.merge(donation);
            em.getTransaction().commit();
            logMethodExit("save", saved);
            return saved;
        } catch (Exception e) {
            em.getTransaction().rollback();
            logMethodExit("save", e);
            throw e;
        }
    }

    @Override
    public void delete(Long id) {
        logMethodEntry("delete", id);
        try {
            Donation donation = em.find(Donation.class, id);
            if (donation != null) {
                em.getTransaction().begin();
                em.refresh(donation);
                em.remove(donation);
            }
        } catch (Exception e) {
            logMethodExit("Error while deleting", e);
        }
    }

    @Override
    public Optional<Donation> findById(Long id) {
        logMethodEntry("findById", id);
        try {
            Donation donation = em.find(Donation.class, id);
            logMethodExit("findById", donation);
            return Optional.of(donation);
        } catch (Exception e) {
            logError("Error finding donation by Id", e);
            return Optional.empty();
        }
    }

    @Override
    public List<Donation> findAll() {
        logMethodEntry("findAll");
        try {
            TypedQuery<Donation> query = em.createQuery(
                    "SELECT d FROM Donation d", Donation.class
            );

            List<Donation> donations = query.getResultList();
            logMethodExit("findAll", donations);
            return donations;
        } catch (Exception e) {
            logError("Error finding all donations", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Donation> findByDonationId(Long id) {
        logMethodEntry("findByDonationId", id);
        try {
            TypedQuery<Donation> query = em.createQuery(
                    "SELECT d FROM Donation d WHERE d.donor.id = :id", Donation.class
            );
            query.setParameter("id", id).getResultList();
            List<Donation> donations = query.getResultList();
            logMethodExit("findByDonationId", donations);
            return donations;
        } catch (Exception e) {
            logError("Error finding all donations", e);
            return Collections.emptyList();
        }
    }

    @Override
    public List<Donation> findByRecipientId(Long id) {
        logMethodEntry("findByRecipientId", id);
        try {
            TypedQuery<Donation> query = em.createQuery(
                    "SELECT d FROM Donation d WHERE d.recipient.id = :id", Donation.class
            );

            query.setParameter("id", id);
            List<Donation> donations = query.getResultList();
            logMethodExit("findByRecipientId", donations);
            return donations;
        } catch (Exception e) {
            logError("Error finding all donations", e);
            return Collections.emptyList();
        }
    }
}
