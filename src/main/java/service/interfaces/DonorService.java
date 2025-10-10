package service.interfaces;

import dto.DonorDTO;
import entity.Donor;
import entity.enums.BloodType;

import java.util.List;
import java.util.Optional;

public interface DonorService {

    public boolean isEligible(Donor donor);
    public boolean canDonate(Donor donor);
    public void updateDonorStatus(Donor donor);

    // service methods
    public DonorDTO createDonor(DonorDTO dto);
    public List<DonorDTO> getAllDonors();
    public Optional<DonorDTO> getDonorById(Long id);
    public DonorDTO updateDonor(DonorDTO donorDTO);
    public void deleteDonor(Long id);

    public List<DonorDTO> getAvailableDonors();
    public Optional<DonorDTO> getEligibleDonors();
    public List<DonorDTO> getDonorsByBloodType(BloodType bloodType);

    // helpers
    public boolean isCinUnique(String cin);
    public Optional<DonorDTO> getDonorByCin(String cin);
}
