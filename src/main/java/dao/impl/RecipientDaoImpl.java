package dao.impl;

import config.DIContainer;
import dao.interfaces.RecipientDao;
import entity.Recipient;
import entity.enums.BloodType;
import utils.Loggable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RecipientDaoImpl extends Loggable implements RecipientDao {
    private final EntityManager em;

    public RecipientDaoImpl() {
        EntityManagerFactory emf = DIContainer.getInstance().getBean(EntityManagerFactory.class);
        this.em = emf.createEntityManager();
    }


    @Override
    public Recipient save(Recipient rec) {
        logMethodEntry("save", rec);
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            if (rec.getId() == null) {
                em.persist(rec);
            } else {
                rec = em.merge(rec);
            }
            tx.commit();
            logMethodExit("save", rec);
            return rec;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            logError("Error saving recipient", e);
            throw new RuntimeException("Failed to save recipient", e);
        }
    }

    @Override
    public Recipient update(Recipient rec) {
        logMethodEntry("update", rec);
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Recipient updated = em.merge(rec);
            tx.commit();
            logMethodExit("update", rec);
            return updated;
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            logError("Error updating recipient", e);
            throw new RuntimeException("Failed to update recipient", e);
        }
    }

    @Override
    public void delete(Long id) {
        logMethodEntry("delete", id);
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            Recipient r = em.find(Recipient.class, id);
            if (r != null) {
                em.remove(r);
            }
            tx.commit();
            logMethodExit("delete", id);
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            logError("Error deleting recipient", e);
            throw new RuntimeException("Failed to delete recipient", e);
        }
    }

    @Override
    public Optional<Recipient> findById(Long id) {
        logMethodEntry("findById", id);
        try {
            Recipient r = em.find(Recipient.class, id);
            logMethodExit("findById", id);
            return Optional.ofNullable(r);
        } catch (Exception e) {
            logError("Error finding recipient by ID", e);
            return Optional.empty();
        }
    }

    @Override
    public List<Recipient> findAll() {
        logMethodEntry("findAll");
        try {
            TypedQuery<Recipient> query = em.createQuery(
                    "SELECT r FROM Recipient r ORDER BY r.situation DESC, r.created_at DESC", Recipient.class);
            List<Recipient> results = query.getResultList();
            logMethodExit("lsAll", results.size() + " recipients found");
            return results;
        } catch (Exception e) {
            logError("Error listing all recipients", e);
            throw new RuntimeException("Failed to retrieve recipients", e);
        }
    }

    @Override
    public List<Recipient> findByBloodType(BloodType bloodType) {
        logMethodEntry("findByBloodType", bloodType);
        try {
            TypedQuery<Recipient> query = em.createQuery(
                    "SELECT r FROM Recipient r WHERE r.blod_type = :blood_type ORDER BY r.created_at DESC", Recipient.class
            );

            query.setParameter("blood_type", bloodType);
            List<Recipient> recipients = query.getResultList();
            logMethodExit("findByBloodType", recipients.size() + " recipients found");
            return recipients;
        } catch (Exception e) {
            logError("Error finding recipients by blood type", e);
            throw new RuntimeException("Failed to retrieve recipients by blood type", e);
        }
    }

    @Override
    public List<Recipient> findWaitingRecipients() {
        logMethodEntry("findWaitingRecipients");
        try {
            TypedQuery<Recipient> query = em.createQuery(
                    "SELECT r FROM Recipient r WHERE r.state = entity.enums.State.WAITING ORDER BY r.created_at DESC", Recipient.class
            );
            List<Recipient> recipients = query.getResultList();
            logMethodExit("findWaitingRecipients", recipients.size() + " recipients found");
            return recipients;
        } catch (Exception e) {
            logError("Error finding waiting recipients", e);
            throw new RuntimeException("Failed to retrieve waiting recipients", e);
        }
    }

    @Override
    public List<Recipient> findByPriority() {
        return Collections.emptyList();
    }
}
