package dao.interfaces;

import entity.Donor;

import java.util.List;
import java.util.Optional;

interface DonorDao {
    // Basic Crud
    Donor save(Donor donor);
    Optional<Donor> findById(Long id);
    List<Donor> lsAll();
    Donor update(Donor donor);
    void delete(Long id);
}
