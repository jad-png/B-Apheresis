package service.impl;

import dao.interfaces.DonorDao;
import dto.DonorDTO;
import entity.Donor;
import entity.enums.BloodType;
import mapper.DonorMapper;
import service.interfaces.DonorService;
import utils.Loggable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DonorServiceImpl extends Loggable implements DonorService {
    private final DonorDao dao;
    private final DonorMapper mapper;

    public DonorServiceImpl(DonorDao dao, DonorMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public boolean isEligible(Donor donor) {
        logMethodEntry("isEligible", donor);
        boolean result = donor != null && donor.isEligible();
        logMethodExit("isEligible", result);
        return result;
    }

    @Override
    public boolean canDonate(Donor donor) {
        logMethodEntry("canDonate", donor);
        boolean result = donor != null && donor.canDonate();
        logMethodExit("canDonate", result);
        return result;
    }

    @Override
    public void updateDonorStatus(Donor donor) {
        logMethodEntry("updateDonorStatus", donor);
        if (donor != null) {
            donor.updateStatus();
            logMethodExit("updateDonorStatus", donor.getStatus());
        }
    }

    @Override
    public DonorDTO createDonor(DonorDTO dto) {
        return null;
    }

    @Override
    public List<DonorDTO> getAllDonors() {
        return Collections.emptyList();
    }

    @Override
    public Optional<DonorDTO> getDonorById(Long id) {
        return Optional.empty();
    }

    @Override
    public DonorDTO updateDonor(DonorDTO donorDTO) {
        return null;
    }

    @Override
    public void deleteDonor(Long id) {

    }

    @Override
    public List<DonorDTO> getAvailableDonors() {
        return Collections.emptyList();
    }

    @Override
    public Optional<DonorDTO> getEligibleDonors() {
        return Optional.empty();
    }

    @Override
    public List<DonorDTO> getDonorsByBloodType(BloodType bloodType) {
        return Collections.emptyList();
    }

    @Override
    public boolean isCinUnique(String cin) {
        return false;
    }

    @Override
    public Optional<DonorDTO> getDonorByCin(String cin) {
        return Optional.empty();
    }
}
