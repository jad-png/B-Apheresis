package dao.impl;

import config.DIContainer;
import dao.interfaces.RecipientDao;
import entity.Recipient;
import entity.enums.BloodType;
import entity.enums.Situation;
import entity.enums.State;
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

    public RecipientDaoImpl(EntityManager em) {
        this.em = em;
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
        logMethodEntry("findByPriority");
        try {
            TypedQuery<Recipient> query = em.createQuery(
                    "SELECT r FROM Recipient r " +
                            "ORDER BY " +
                            "CASE r.situation" +
                            "  WHEN entity.enums.Situation.CRITICAL THEN 3 " +
                            "  WHEN entity.enums.Situation.URGENT THEN 2 " +
                            "  WHEN entity.enums.Situation.NORMAL THEN 1 " +
                            " ELSE 0 END DESC, r.created_at DESC ",
                    Recipient.class
            );

            List<Recipient> recipients = query.getResultList();
            logMethodExit("findByPriority", recipients.size() + " recipients found");
            return recipients;
        } catch (Exception e) {
            logError("Error finding recipients by priority", e);
            throw new RuntimeException("Failed to retrieve recipients by priority", e);
        }
    }

    @Override
    public List<Recipient> findByState(State state) {
        logMethodEntry("findByState", state);
        try {
            TypedQuery<Recipient> query = em.createQuery(
                    "SELECT r FROM Recipient r WHERE r.state = :state order by r.created_at desc", Recipient.class
            );

            query.setParameter("state", state);
            List<Recipient> recipients = query.getResultList();
            logMethodExit("findByState", recipients.size() + " recipients found");
            return recipients;
        } catch (Exception e) {
            logError("Error finding recipients by state", e);
            throw new RuntimeException("Failed to retrieve recipients by state", e);
        }
    }

    @Override
    public List<Recipient> findBySituation(Situation situation) {
        logMethodEntry("findBySituation", situation);
        try {
            TypedQuery<Recipient> query = em.createQuery(
                    "SELECT r FROM Recipient r WHERE r.situation = :situation order by r.created_at desc", Recipient.class
            );

            query.setParameter("situation", situation);
            List<Recipient> recipients = query.getResultList();
            logMethodExit("findBySituation", recipients.size() + " recipients found");
            return recipients;
        } catch (Exception e) {
            logError("Error finding recipients by situation", e);
            throw new RuntimeException("Failed to retrieve recipients by situation", e);
        }
    }

    @Override
    public List<Recipient> findInsatisfiedRecipients() {
        logMethodEntry("findInsatisfiedRecipients");
        try {
            TypedQuery<Recipient> query = em.createQuery(
                    "SELECT r FROM Recipient r WHERE r.state = entity.enums.State.WAITING order by r.created_at desc ", Recipient.class
            );
            List<Recipient> recipients = query.getResultList();
            logMethodExit("findInsatisfiedRecipients", recipients.size() + " recipients found");
            return recipients;
        } catch (Exception e) {
            logError("Error finding unsatisfied recipients", e);
            throw new RuntimeException("Failed to retrieve unsatisfied recipients", e);
        }
    }

    @Override
    public boolean existsByCin(String cin) {
        logMethodEntry("existsByCin", cin);
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT count(r) from Recipient r WHERE r.cin = :cin order by r.created_at desc", Long.class
            );
            query.setParameter("cin", cin);
            Long count = query.getSingleResult();
            boolean exists = count > 0;
            logMethodExit("existsByCin", exists);
            return exists;
        } catch (Exception e) {
            logError("Error checking recipient existence by CIN", e);
            return false;
        }
    }

    @Override
    public Long CountByState(State state) {
        logMethodEntry("CountByState", state);
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(r) FROM Recipient r WHERE r.state = :state ORDER BY r.created_at desc", Long.class
            );
            query.setParameter("state", state);
            Long count = query.getSingleResult();
            logMethodExit("countByState", count);
            return count;
        } catch (Exception e) {
            logError("Error counting recipients by state", e);
            throw new RuntimeException("Failed to count recipients by state", e);
        }
    }
}
