package dao.impl;

import config.DIContainer;
import entity.Donor;
import entity.enums.BloodType;
import entity.enums.Status;
import utils.JPAUtils;
import utils.Loggable;
import dao.interfaces.DonorDao;

import javax.persistence.*;
import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DonorDaoImpl extends Loggable implements DonorDao {
    private final EntityManager em;

    public DonorDaoImpl(EntityManager em) {
        this.em = em;
    }

    // Basic Crud queries
    @Override
    public Donor save(Donor donor) {
        logMethodEntry("save", donor);
        EntityTransaction tx = em.getTransaction();

        try {
            if (!tx.isActive()) { tx.begin(); }

                if (donor.getId() == null) {
                em.persist(donor);
            } else {
                donor = em.merge(donor);
            }
            tx.commit();
            logMethodExit("save", donor);
            return donor;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            logError("Error saving donor", e);
            throw new RuntimeException("Failed to save donor", e);
        }
    }

    @Override
    public Optional<Donor> findById(Long id) {
        logMethodEntry("findById", id);
        try {
            Donor donor = em.find(Donor.class, id);
            logMethodExit("findById", id);
            return Optional.ofNullable(donor);
        } catch (Exception e) {
            logError("Error finding donor by Id", e);
            return Optional.empty();
        }
    }

    @Override
    public List<Donor> lsAll() {
        logMethodEntry("lsAll");
        try {
            TypedQuery<Donor> query = em.createQuery(
                    "SELECT d FROM Donor d", Donor.class
            );
            List<Donor> donors = query.getResultList();
            logMethodExit("findAll", donors.size() + " donors found");
            return donors;
        } catch (Exception e) {
            logError("Error finding all donors", e);
            throw new RuntimeException("Failed to retrieve donors", e);
        }
    }

    @Override
    public Donor update(Donor donor) {
        logMethodEntry("update", donor);
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Donor updatedDonor = em.merge(donor);
            tx.commit();
            logMethodExit("update", updatedDonor);
            return updatedDonor;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            logError("Error updating donor", e);
            throw new RuntimeException("Failed to update donor", e);
        }
    }

    @Override
    public void delete(Long id) {
        logMethodEntry("delete", id);
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Donor donor = em.find(Donor.class, id);
            if (donor != null) {
                em.remove(donor);
            }
            tx.commit();
            logMethodExit("delete", id);
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            logError("Error deleting donor", e);
            throw new RuntimeException("Failed to delete donor", e);
        }
    }

    // Business specific queries
    @Override
    public List<Donor> findByStatus(Status status) {
        logMethodEntry("findByStatus", status);
        try {
            TypedQuery<Donor> query = em.createQuery(
                    "SELECT d FROM Donor d WHERE d.status = :status ORDER BY d.created_at DESC", Donor.class
            );
            query.setParameter("status", status);
            List<Donor> donors = query.getResultList();
            logMethodExit("findByStatus", donors.size() + " donors found");
            return donors;
        } catch (Exception e) {
            logError("Error finding donors by status", e);
            throw new RuntimeException("Failed to retrieve donors by status", e);
        }
    }

    @Override
    public List<Donor> findByBloodType(BloodType bloodType) {
        logMethodEntry("findByBloodType", bloodType);
        try {
            TypedQuery<Donor> query = em.createQuery(
                    "SELECT d FROM Donor d WHERE d.blod_type = :bloodType ORDER BY d.created_at DESC", Donor.class);
            query.setParameter("bloodType", bloodType);
            List<Donor> donors = query.getResultList();
            logMethodExit("findByBloodType", donors.size() + " donors found");
            return donors;
        } catch (Exception e) {
            logError("Error finding donors by blood type", e);
            throw new RuntimeException("Failed to retrieve donors by blood type", e);
        }
    }

    @Override
    public List<Donor> findByMedicalCondition(String condition) {
        logMethodEntry("findByMedicalCondition", condition);
        try {
            TypedQuery<Donor> query = em.createQuery(
                    "SELECT d FROM Donor d WHERE d.mdCondition = :condition ORDER BY d.created_at DESC", Donor.class);
            query.setParameter("condition", condition);
            List<Donor> donors = query.getResultList();
            logMethodExit("findByMedicalCondition", donors.size() + " donors found");
            return donors;
        } catch (Exception e) {
            logError("Error finding donors by medical condition", e);
            throw new RuntimeException("Failed to retrieve donors by medical condition", e);
        }
    }

    @Override
    public Optional<Donor> findByCin(String cin) {
        logMethodEntry("findByCin", cin);
        try {
            TypedQuery<Donor> query = em.createQuery(
                    "SELECT d FROM Donor d WHERE d.cin = :cin", Donor.class);
            query.setParameter("cin", cin);
            Donor donor = query.getResultStream().findFirst().orElse(null);
            logMethodExit("findByCin", donor);
            return Optional.ofNullable(donor);
        } catch (Exception e) {
            logError("Error finding donor by CIN", e);
            return Optional.empty();
        }
    }

    @Override
    public List<Donor> findEligibleDonors() {
        logMethodEntry("findEligibleDonors");
        try {
            TypedQuery<Donor> query = em.createQuery(
                    "SELECT d FROM Donor d WHERE d.status = entity.enums.Status.AVAILABLE " +
                            "ORDER BY d.created_at DESC", Donor.class);
            List<Donor> donors = query.getResultList();
            logMethodExit("findEligibleDonors", donors.size() + " donors found");
            return donors;
        } catch (Exception e) {
            logError("Error finding eligible donors", e);
            throw new RuntimeException("Failed to retrieve eligible donors", e);
        }
    }

    @Override
    public List<Donor> findAvailableDonors() {
        logMethodEntry("findAvailableDonors");
        try {
            TypedQuery<Donor> query = em.createQuery(
                    "SELECT d FROM Donor d WHERE d.status = entity.enums.Status.AVAILABLE " +
                            "AND d.recipient IS NULL ORDER BY d.created_at DESC", Donor.class);
            List<Donor> donors = query.getResultList();
            logMethodExit("findAvailableDonors", donors.size() + " donors found");
            return donors;
        } catch (Exception e) {
            logError("Error finding available donors", e);
            throw new RuntimeException("Failed to retrieve available donors", e);
        }
    }

    @Override
    public List<Donor> findByRecipient(Long recipientId) {
        logMethodEntry("findByRecipient", recipientId);
        try {
            TypedQuery<Donor> query = em.createQuery("SELECT d FROM Donor d WHERE d.recipient.id = :recipientId ORDER BY d.created_at DESC", Donor.class);
            query.setParameter("recipientId", recipientId);
            List<Donor> donors =  query.getResultList();
            logMethodExit("findByRecipient", donors.size() + " donors found");
            return donors;
        } catch (Exception e) {
            logError("Error finding donors by recipient", e);
            throw new RuntimeException("Failed to retrieve donors by recipient", e);
        }
    }

    // Advanced queries
    @Override
    public List<Donor> findCompatibleDonors(BloodType recipientBloodType) {
        logMethodEntry("findCompatibleDonors", recipientBloodType);
        try {
            TypedQuery<Donor> query = em.createQuery(
                    "SELECT d FROM Donor d WHERE d.status = entity.enums.Status.AVAILABLE " +
                            "AND d.recipient IS NULL ORDER BY d.created_at DESC", Donor.class
            );

            List<Donor> donors = query.getResultList();
            logMethodExit("findCompatibleDonors", donors.size() + " potential donors found");
            return donors;
        } catch (Exception e) {
            logError("Error finding compatible donors", e);
            throw new RuntimeException("Failed to retrieve compatible donors", e);
        }
    }

    @Override
    public List<Donor> findDonorsWithNoRecipient() {
        logMethodEntry("findDonorsWithNoRecipient");
        try {
            TypedQuery<Donor> query = em.createQuery(
                    "SELECT d FROM Donor d WHERE d.recipient IS NULL ORDER BY d.created_at DESC", Donor.class
            );

            List<Donor> donors = query.getResultList();
            logMethodEntry("findDonorsWithNoRecipient", donors.size() + " donors found");
            return donors;
        } catch (Exception e) {
            logError("Error finding donors with no recipient", e);
            throw new RuntimeException("Failed to retrieve donors with no recipient", e);
        }
    }

    @Override
    public Long countByStatus(Status status) {
        logMethodEntry("countByStatus", status);
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(d) FROM Donor d WHERE d.status = :status", Long.class);
            query.setParameter("status", status);
            Long count = query.getSingleResult();
            logMethodExit("countByStatus", count);
            return count;
        } catch (Exception e) {
            logError("Error counting donors by status", e);
            throw new RuntimeException("Failed to count donors by status", e);
        }
    }

    @Override
    public boolean existsByCin(String cin) {
        logMethodEntry("existsByCin", cin);
        try {
            TypedQuery<Long> query = em.createQuery(
                    "SELECT COUNT(d) FROM Donor d WHERE d.cin = :cin", Long.class);
            query.setParameter("cin", cin);
            Long count = query.getSingleResult();
            boolean exists = count > 0;
            logMethodExit("existsByCin", exists);
            return exists;
        } catch (Exception e) {
            logError("Error checking if donor exists by CIN", e);
            return false;
        }
    }

    public void close() {
        em.close();
    }
}
