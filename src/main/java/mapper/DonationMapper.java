package mapper;

import dto.DonationDTO;
import entity.Donation;

public class DonationMapper {
    public DonationDTO toDto (Donation donation) {
        if (donation == null) return null;

        DonationDTO dto = new DonationDTO();
        dto.setId(donation.getId());
        dto.setDonorId(donation.getDonor().getId());
        dto.setRecipientId(donation.getRecipient().getId());

        return dto;
    }

    
}
