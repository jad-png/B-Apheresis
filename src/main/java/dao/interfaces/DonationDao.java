package dao.interfaces;

import dto.DonationDTO;
import entity.Donation;

import java.util.List;
import java.util.Optional;

public interface DonationDao {
    Donation save(Donation donation);
    void delete (Long id);
    Optional<Donation> findById(Long id);
    List<Donation> findAll();
    List<Donation> findByDonationId(Long id);
    List<Donation> findByRecipientId(Long id);
}
