package service.interfaces;

import dto.DonationDTO;

import java.util.List;
import java.util.Optional;

public interface DonationService {
    DonationDTO saveDonation(DonationDTO dto);
    void deleteDonation(Long id);
    Optional<DonationDTO> getDonation(Long id);
    List<DonationDTO> getAllDonations();
    List<DonationDTO> getByDonatorId(Long donatorId);
    List<DonationDTO> getByRecipientId(Long id);
}
