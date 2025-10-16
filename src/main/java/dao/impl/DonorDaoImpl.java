package dao.impl;

import dao.interfaces.DonorDao;
import entity.Donor;
import entity.enums.BloodType;
import entity.enums.Status;
import utils.Loggable;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

public class DonorDaoImpl extends Loggable implements DonorDao {
    private final EntityManagerFactory emf;

    public DonorDaoImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    // Util method to get EntityManager
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    // Basic CRUD
    @Override
    public Donor save(Donor donor) {
        logMethodEntry("save", donor);
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            if (donor.getId() == null) {
                em.persist(donor);
            } else {
                donor = em.merge(donor);
            }
            tx.commit();
            logMethodExit("save", donor);
            return donor;
        } catch (Exception e) {
            tx.rollback();
            logError("Error in save", e);
            throw new RuntimeException("Error saving donor", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Donor> findById(Long id) {
        logMethodEntry("findById", id);
        EntityManager em = getEntityManager();
        try {
            Donor donor = em.find(Donor.class, id);
            logMethodExit("findById", donor);
            return Optional.ofNullable(donor);
        } catch (Exception e) {
            logError("Error finding donor by ID", e);
            return Optional.empty();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Donor> lsAll() {
        logMethodEntry("lsAll");
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Donor> query = em.createQuery("SELECT d FROM Donor d", Donor.class);
            List<Donor> donors = query.getResultList();
            logMethodExit("lsAll", donors.size() + " donors found");
            return donors;
        } finally {
            em.close();
        }
    }

    @Override
    public Donor update(Donor donor) {
        logMethodEntry("update", donor);
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Donor updatedDonor = em.merge(donor);
            tx.commit();
            logMethodExit("update", updatedDonor);
            return updatedDonor;
        } catch (Exception e) {
            tx.rollback();
            logError("Error updating donor", e);
            throw new RuntimeException("Error updating donor", e);
        } finally {
            em.close();
        }
    }

    @Override
    public boolean delete(Long id) {
        logMethodEntry("delete", id);
        EntityManager em = getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Donor donor = em.find(Donor.class, id);
            if (donor != null) {
                em.remove(donor);
                tx.commit();
                logMethodExit("delete", "deleted");
                return true;
            }
            tx.rollback();
            logMethodExit("delete", "not found");
            return false;
        } catch (Exception e) {
            tx.rollback();
            logError("Error deleting donor", e);
            throw new RuntimeException("Error deleting donor", e);
        } finally {
            em.close();
        }
    }

    // Business Queries
    @Override
    public List<Donor> findByStatus(Status status) {
        logMethodEntry("findByStatus", status);
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Donor> query = em.createQuery(
                    "SELECT d FROM Donor d WHERE d.status = :status ORDER BY d.createdAt DESC", Donor.class);
            query.setParameter("status", status);
            List<Donor> donors = query.getResultList();
            logMethodExit("findByStatus", donors.size() + " donors found");
            return donors;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Donor> findByBloodType(BloodType bloodType) {
        logMethodEntry("findByBloodType", bloodType);
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Donor> query = em.createQuery(
                    "SELECT d FROM Donor d WHERE d.bloodType = :bloodType ORDER BY d.createdAt DESC", Donor.class);
            query.setParameter("bloodType", bloodType);
            List<Donor> donors = query.getResultList();
            logMethodExit("findByBloodType", donors.size() + " donors found");
            return donors;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Donor> findByMedicalCondition(String condition) {
        logMethodEntry("findByMedicalCondition", condition);
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Donor> query = em.createQuery(
                    "SELECT d FROM Donor d WHERE d.mdCondition = :condition ORDER BY d.createdAt DESC", Donor.class);
            query.setParameter("condition", condition);
            List<Donor> donors = query.getResultList();
            logMethodExit("findByMedicalCondition", donors.size() + " donors found");
            return donors;
        } finally {
            em.close();
        }
    }

    @Override
    public Optional<Donor> findByCin(String cin) {
        logMethodEntry("findByCin", cin);
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Donor> query = em.createQuery(
                    "SELECT d FROM Donor d WHERE d.cin = :cin", Donor.class);
            query.setParameter("cin", cin);
            Donor donor = query.getResultStream().findFirst().orElse(null);
            logMethodExit("findByCin", donor);
            return Optional.ofNullable(donor);
        } finally {
            em.close();
        }
    }

    @Override
    public List<Donor> findEligibleDonors() {
        logMethodEntry("findEligibleDonors");
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Donor> query = em.createQuery(
                    "SELECT d FROM Donor d WHERE d.status = :status ORDER BY d.createdAt DESC", Donor.class);
            query.setParameter("status", Status.AVAILABLE);
            List<Donor> donors = query.getResultList();
            logMethodExit("findEligibleDonors", donors.size() + " donors found");
            return donors;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Donor> findAvailableDonors() {
        logMethodEntry("findAvailableDonors");
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Donor> query = em.createQuery(
                    "SELECT d FROM Donor d WHERE d.status = :status AND d.recipient IS NULL ORDER BY d.createdAt DESC",
                    Donor.class);
            query.setParameter("status", Status.AVAILABLE);
            List<Donor> donors = query.getResultList();
            logMethodExit("findAvailableDonors", donors.size() + " donors found");
            return donors;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Donor> findByRecipient(Long recipientId) {
        logMethodEntry("findByRecipient", recipientId);
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Donor> query = em.createQuery(
                    "SELECT d FROM Donor d WHERE d.recipient.id = :recipientId ORDER BY d.createdAt DESC", Donor.class);
            query.setParameter("recipientId", recipientId);
            List<Donor> donors = query.getResultList();
            logMethodExit("findByRecipient", donors.size() + " donors found");
            return donors;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Donor> findCompatibleDonors(BloodType recipientBloodType) {
        logMethodEntry("findCompatibleDonors", recipientBloodType);
        // NOTE: Here you should implement real blood type compatibility logic.
        // For now, it behaves like findAvailableDonors
        return findAvailableDonors();
    }

    @Override
    public List<Donor> findDonorsWithNoRecipient() {
        logMethodEntry("findDonorsWithNoRecipient");
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Donor> query = em.createQuery(
                    "SELECT d FROM Donor d WHERE d.recipient IS NULL ORDER BY d.createdAt DESC", Donor.class);
            List<Donor> donors = query.getResultList();
            logMethodExit("findDonorsWithNoRecipient", donors.size() + " donors found");
            return donors;
        } finally {
            em.close();
        }
    }

    @Override
    public Long countByStatus(Status status) {
        logMethodEntry("countByStatus", status);
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(d) FROM Donor d WHERE d.status = :status", Long.class);
            query.setParameter("status", status);
            Long count = query.getSingleResult();
            logMethodExit("countByStatus", count);
            return count;
        } finally {
            em.close();
        }
    }

    @Override
    public boolean existsByCin(String cin) {
        logMethodEntry("existsByCin", cin);
        EntityManager em = getEntityManager();
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(d) FROM Donor d WHERE d.cin = :cin", Long.class);
            query.setParameter("cin", cin);
            Long count = query.getSingleResult();
            boolean exists = count > 0;
            logMethodExit("existsByCin", exists);
            return exists;
        } finally {
            em.close();
        }
    }

    public void closeFactory() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
