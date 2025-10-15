package mapper;

import dto.DonationDTO;
import entity.Account;
import entity.Donation;
import entity.Donor;
import entity.Recipient;

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

        Donor donor = new Donor();
        donor.setId(dto.getDonorId());
        donation.setDonor(donor);

        Recipient recipient = new Recipient();
        recipient.setId(dto.getRecipientId());
        donation.setRecipient(recipient);
        
        return donation;
    }
}
