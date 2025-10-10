package mapper;

import dto.DonorDTO;
import entity.Donor;
import entity.enums.MedicalCondition;
import utils.Loggable;

public class DonorMapper {
    public static DonorDTO toDto(Donor donor) {
        if (donor == null) {
            return null;
        }

        DonorDTO dto = new DonorDTO();
        dto.setId(donor.getId());
        dto.setFirstName(donor.getFirstName());
        dto.setLastName(donor.getLastName());
        dto.setCin(donor.getCin());
        dto.setBirthday(donor.getBirthday());
        dto.setBloodType(donor.getBloodType());
        dto.setGender(donor.getGender());
        dto.setWeight(donor.getWeight());
        dto.setLastDonationDate(donor.getLastDonationDate());
        dto.setMedicalCondition(donor.getMedicalCondition() != null ? donor.getMedicalCondition() : MedicalCondition.HEALTHY);

        return dto;
    }

    public static Donor toEntity(DonorDTO dto) {
        if (dto == null) {
            return null;
        }

        Donor donor = new Donor();
        donor.setId(dto.getId());
        donor.setFirstName(dto.getFirstName());
        donor.setLastName(dto.getLastName());
        donor.setCin(dto.getCin());
        donor.setBirthday(dto.getBirthday());
        donor.setBloodType(dto.getBloodType());
        donor.setGender(dto.getGender());
        donor.setWeight(dto.getWeight());
        donor.setLastDonationDate(dto.getLastDonationDate());
        donor.setMedicalCondition(dto.getMedicalCondition() != null ? donor.getMedicalCondition() : MedicalCondition.HEALTHY);

        return donor;
    }
}
