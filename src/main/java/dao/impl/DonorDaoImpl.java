package dao.impl;

import config.DIContainer;
import entity.Donor;
import entity.enums.BloodType;
import entity.enums.Status;
import utils.JPAUtils;
import utils.Loggable;
import dao.interfaces.DonorDao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DonorDaoImpl extends Loggable implements DonorDao {
    private final EntityManager em;

    public DonorDaoImpl() {
        EntityManagerFactory emf = DIContainer.getInstance().getBean(EntityManagerFactory.class);
        this.em = emf.createEntityManager();
    }

    // Basic Crud queries
    @Override
    public Donor save(Donor donor) {
        return null;
    }

    @Override
    public Optional<Donor> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Donor> lsAll() {
        return Collections.emptyList();
    }

    @Override
    public Donor update(Donor donor) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    // Business specific queries
    @Override
    public List<Donor> findByStatus(Status status) {
        return Collections.emptyList();
    }

    @Override
    public List<Donor> findByBloodType(BloodType bloodType) {
        return Collections.emptyList();
    }

    @Override
    public List<Donor> findByMedicalCondition(String condition) {
        return Collections.emptyList();
    }

    @Override
    public Optional<Donor> findByCin(String cin) {
        return Optional.empty();
    }

    @Override
    public List<Donor> findEligibleDonors() {
        return Collections.emptyList();
    }

    @Override
    public List<Donor> findAvailableDonors() {
        return Collections.emptyList();
    }

    @Override
    public List<Donor> findByRecipient(Long recipientId) {
        return Collections.emptyList();
    }

    // Advanced queries
    @Override
    public List<Donor> findCompatibleDonors(BloodType recipientBloodType) {
        return Collections.emptyList();
    }

    @Override
    public List<Donor> findDonorsWithNoRecipient() {
        return Collections.emptyList();
    }

    @Override
    public Long countByStatus(Status status) {
        return 0L;
    }

    @Override
    public boolean existsByCin(String cin) {
        return false;
    }
}
