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
import java.util.stream.Collectors;

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
        logMethodEntry("createDonor", dto);

        if (dto == null) {
            logMethodExit("createDonor", "DTO is null");
            throw new IllegalArgumentException("DonorDTO cannot be null");
        }

        // TODO: condition to check if Cin Unique

        Donor donor = mapper.toEntity(dto);
        updateDonorStatus(donor);
        Donor saved = dao.save(donor);

        DonorDTO result = toDTOWithEligibility(saved);
        logMethodExit("CreateDonor", result);
        return result;
    }

    @Override
    public List<DonorDTO> getAllDonors() {
        logMethodEntry("getAllDonors");

        List<Donor> donors = dao.lsAll();
        List<DonorDTO> result = donors.stream()
                .map(this::toDTOWithEligibility).collect(Collectors.toList());
        logMethodExit("getAllDonors", result.size() + " donors found");
        return result;
    }

    @Override
    public Optional<DonorDTO> getDonorById(Long id) {
        logMethodEntry("getDonorById", id);

        Optional<Donor> donorOpt = dao.findById(id);
        Optional<DonorDTO> result = donorOpt.map(this::toDTOWithEligibility);

        logMethodExit("getDonorById", result.isPresent() ? "Found" : "Not Found");
        return result;
    }

    @Override
    public DonorDTO updateDonor(DonorDTO donorDTO) {
        logMethodEntry("updateDonor", donorDTO);

        if (donorDTO == null || donorDTO.getId() == null) {
            throw new IllegalArgumentException("DonorDTO or its ID cannot be null.");
        }

        Donor donor = mapper.toEntity(donorDTO);
        updateDonorStatus(donor);

        Donor updated = dao.update(donor);
        DonorDTO result = toDTOWithEligibility(updated);

        logMethodExit("updateDonor", result);
        return result;
    }

    @Override
    public void deleteDonor(Long id) {
        logMethodEntry("deleteDonor", id);

        dao.delete(id);

        logMethodExit("deleteDonor");
    }

    @Override
    public List<DonorDTO> getAvailableDonors() {
        logMethodEntry("getAvailableDonors");

        List<Donor> donors = dao.findAvailableDonors();
        List<DonorDTO> result = donors.stream()
                .map(this::toDTOWithEligibility)
                .collect(Collectors.toList());;

        logMethodExit("getAvailableDonors", result.size() + " found");
        return result;
    }

    @Override
    public Optional<DonorDTO> getEligibleDonors() {
        logMethodEntry("getEligibleDonors");

        List<Donor> donors = dao.findEligibleDonors();

        // Return just the first eligible donor as Optional (if that's the intent).
        Optional<DonorDTO> result = donors.stream()
                .findFirst()
                .map(this::toDTOWithEligibility);

        logMethodExit("getEligibleDonors", result.isPresent() ? "Found" : "None found");
        return result;
    }

    @Override
    public List<DonorDTO> getDonorsByBloodType(BloodType bloodType) {

        logMethodEntry("getDonorsByBloodType", bloodType);

        List<Donor> donors = dao.findByBloodType(bloodType);
        List<DonorDTO> result = donors.stream()
                .map(this::toDTOWithEligibility)
                .collect(Collectors.toList());

        logMethodExit("getDonorsByBloodType", result.size() + " found");
        return result;
    }

    @Override
    public boolean isCinUnique(String cin) {
        logMethodEntry("isCinUnique", cin);

        boolean unique = !dao.existsByCin(cin);

        logMethodExit("isCinUnique", unique);
        return unique;
    }

    @Override
    public Optional<DonorDTO> getDonorByCin(String cin) {
        logMethodEntry("getDonorByCin", cin);

        Optional<Donor> donorOpt = dao.findByCin(cin);
        Optional<DonorDTO> result = donorOpt.map(this::toDTOWithEligibility);

        logMethodExit("getDonorByCin", result.isPresent() ? "Found" : "Not Found");
        return result;
    }

    // Helper method to convert entity to DTO and add eligibility info
    public DonorDTO toDTOWithEligibility(Donor donor) {
        DonorDTO dto = mapper.toDto(donor);
        dto.setEligible(isEligible(donor));
        dto.setCanDonate(canDonate(donor));
        return dto;
    }
}
