package dao.interfaces;

import entity.Donor;
import entity.enums.BloodType;
import entity.enums.Status;

import java.util.List;
import java.util.Optional;

interface DonorDao {
    // Basic Crud
    Donor save(Donor donor);
    Optional<Donor> findById(Long id);
    List<Donor> lsAll();
    Donor update(Donor donor);
    void delete(Long id);

    // business apecific queries
    List<Donor> findByStatus(Status status);
    List<Donor> findByBloodType(BloodType bloodType);
    List<Donor> findByMedicalCondition(String condition);
    Optional<Donor> findByCin(String cin);
    List<Donor> findEligibleDonors();
    List<Donor> findAvailableDonors();
    List<Donor> findByRecipient(Long recipientId);
}
