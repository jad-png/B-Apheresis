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

    public Donation toEntity (DonationDTO dto) {
        if (dto == null) return null;

        Donation donation = new Donation();

        donation.setId(dto.getId());
        donation.getDonor().setId(dto.getDonorId());
        donation.getRecipient().setId(dto.getRecipientId());

        return donation;
    }
}
