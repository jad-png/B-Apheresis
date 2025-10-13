package dao.impl;

import config.DIContainer;
import dao.interfaces.RecipientDao;
import entity.Account;
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
        if (rec.getId() == null) {
            em.persist(rec);
            logMethodExit("save", rec);
            return rec;
        } else {
            Recipient merged = em.merge(rec);
            logMethodExit("save", merged);
            return merged;
        }
    }

    @Override
    public Recipient update(Recipient rec) {
        logMethodEntry("update", rec);
        Recipient updated = em.merge(rec);
        logMethodExit("update", updated);
        return updated;
    }

    @Override
    public boolean delete(Long id) {
        logMethodEntry("delete", id);
        Recipient r = em.find(Recipient.class, id);
        if (r != null) {
            em.remove(r);
            em.remove(em.find(Account.class, id));
            return true;
        }
        logMethodExit("delete", id);
        return false;
    }

    @Override
    public Optional<Recipient> findById(Long id) {
        logMethodEntry("findById", id);
        Recipient r = em.find(Recipient.class, id);
        logMethodExit("findById", id);
        return Optional.ofNullable(r);
    }

    @Override
    public List<Recipient> findAll() {
        logMethodEntry("findAll");
        TypedQuery<Recipient> query = em.createQuery(
                "SELECT r FROM Recipient r ORDER BY r.situation DESC, r.created_at DESC", Recipient.class);
        List<Recipient> results = query.getResultList();
        logMethodExit("lsAll", results.size() + " recipients found");
        return results;
    }

    @Override
    public List<Recipient> findByBloodType(BloodType bloodType) {
        logMethodEntry("findByBloodType", bloodType);
        TypedQuery<Recipient> query = em.createQuery(
                "SELECT r FROM Recipient r WHERE r.blod_type = :blood_type ORDER BY r.created_at DESC", Recipient.class
        );

        query.setParameter("blood_type", bloodType);
        List<Recipient> recipients = query.getResultList();
        logMethodExit("findByBloodType", recipients.size() + " recipients found");
        return recipients;
    }

    @Override
    public List<Recipient> findWaitingRecipients() {
        logMethodEntry("findWaitingRecipients");
        TypedQuery<Recipient> query = em.createQuery(
                "SELECT r FROM Recipient r WHERE r.state = entity.enums.State.WAITING ORDER BY r.created_at DESC", Recipient.class
        );
        List<Recipient> recipients = query.getResultList();
        logMethodExit("findWaitingRecipients", recipients.size() + " recipients found");
        return recipients;
    }

    @Override
    public List<Recipient> findByPriority() {
        logMethodEntry("findByPriority");
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
    }

    @Override
    public List<Recipient> findByState(State state) {
        logMethodEntry("findByState", state);
        TypedQuery<Recipient> query = em.createQuery(
                "SELECT r FROM Recipient r WHERE r.state = :state order by r.created_at desc", Recipient.class
        );

        query.setParameter("state", state);
        List<Recipient> recipients = query.getResultList();
        logMethodExit("findByState", recipients.size() + " recipients found");
        return recipients;
    }

    @Override
    public List<Recipient> findBySituation(Situation situation) {
        logMethodEntry("findBySituation", situation);
        TypedQuery<Recipient> query = em.createQuery(
                "SELECT r FROM Recipient r WHERE r.situation = :situation order by r.created_at desc", Recipient.class
        );

        query.setParameter("situation", situation);
        List<Recipient> recipients = query.getResultList();
        logMethodExit("findBySituation", recipients.size() + " recipients found");
        return recipients;
    }

    @Override
    public List<Recipient> findInsatisfiedRecipients() {
        logMethodEntry("findInsatisfiedRecipients");
        TypedQuery<Recipient> query = em.createQuery(
                "SELECT r FROM Recipient r WHERE r.state = entity.enums.State.WAITING order by r.created_at desc ", Recipient.class
        );
        List<Recipient> recipients = query.getResultList();
        logMethodExit("findInsatisfiedRecipients", recipients.size() + " recipients found");
        return recipients;
    }

    @Override
    public boolean existsByCin(String cin) {
        logMethodEntry("existsByCin", cin);
        TypedQuery<Long> query = em.createQuery(
                "SELECT count(r) from Recipient r WHERE r.cin = :cin order by r.created_at desc", Long.class
        );
        query.setParameter("cin", cin);
        Long count = query.getSingleResult();
        boolean exists = count > 0;
        logMethodExit("existsByCin", exists);
        return exists;
    }

    @Override
    public Long CountByState(State state) {
        logMethodEntry("CountByState", state);
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(r) FROM Recipient r WHERE r.state = :state ORDER BY r.created_at desc", Long.class
        );
        query.setParameter("state", state);
        Long count = query.getSingleResult();
        logMethodExit("countByState", count);
        return count;
    }
}
