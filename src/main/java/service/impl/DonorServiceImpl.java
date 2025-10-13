package service.impl;

import dao.interfaces.DonorDao;
import dto.DonorDTO;
import entity.Donor;
import entity.enums.BloodType;
import mapper.DonorMapper;
import service.interfaces.DonorService;
import utils.Loggable;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DonorServiceImpl extends Loggable implements DonorService {
    private final DonorDao dao;
    private final DonorMapper mapper;
    private final EntityManager em;

    public DonorServiceImpl(DonorDao dao, DonorMapper mapper, EntityManager em) {
        this.dao = dao;
        this.mapper = mapper;
        this.em = em;
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

        EntityTransaction tx = em.getTransaction();
        try {
            if (!tx.isActive()) {
                tx.begin();
            }

            Donor donor = mapper.toEntity(dto);
            updateDonorStatus(donor);
            Donor saved = dao.save(donor);

            tx.commit();

            DonorDTO result = toDTOWithEligibility(saved);
            logMethodExit("createDonor", result);
            return result;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            logError("Failed to create donor", e);
            throw new RuntimeException("Failed to create donor", e);
        }
    }

    @Override
    public List<DonorDTO> getAllDonors() {
        logMethodEntry("getAllDonors");

        try {
            List<Donor> donors = dao.lsAll();
            List<DonorDTO> result = donors.stream()
                    .map(this::toDTOWithEligibility)
                    .collect(Collectors.toList());
            logMethodExit("getAllDonors", result.size() + " donors found");
            return result;
        } catch (Exception e) {
            logError("Failed to get all donors", e);
            throw new RuntimeException("Failed to get all donors", e);
        }
    }

    @Override
    public Optional<DonorDTO> getDonorById(Long id) {
        logMethodEntry("getDonorById", id);

        try {
            Optional<Donor> donorOpt = dao.findById(id);
            Optional<DonorDTO> result = donorOpt.map(this::toDTOWithEligibility);

            logMethodExit("getDonorById", result.isPresent() ? "Found" : "Not Found");
            return result;
        } catch (Exception e) {
            logError("Failed to get donor by id", e);
            throw new RuntimeException("Failed to get donor by id", e);
        }
    }

    @Override
    public DonorDTO updateDonor(DonorDTO donorDTO) {
        logMethodEntry("updateDonor", donorDTO);

        if (donorDTO == null || donorDTO.getId() == null) {
            throw new IllegalArgumentException("DonorDTO or its ID cannot be null.");
        }

        EntityTransaction tx = em.getTransaction();
        try {
            if (!tx.isActive()) {
                tx.begin();
            }
            Donor donor = mapper.toEntity(donorDTO);
            updateDonorStatus(donor);
            Donor updated = dao.update(donor);

            tx.commit();

            DonorDTO result = toDTOWithEligibility(updated);
            logMethodExit("updateDonor", result);
            return result;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            logError("Failed to update donor", e);
            throw new RuntimeException("Failed to update donor", e);
        }
    }

    @Override
    public void deleteDonor(Long id) {
        logMethodEntry("deleteDonor", id);

        EntityTransaction tx = em.getTransaction();
        try {
            if (!tx.isActive()) {
                tx.begin();
            }
            dao.delete(id);

            tx.commit();

            logMethodExit("deleteDonor");
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            logError("Failed to delete donor", e);
            throw new RuntimeException("Failed to delete donor", e);
        }
    }

    @Override
    public List<DonorDTO> getAvailableDonors() {
        logMethodEntry("getAvailableDonors");

        try {
            List<Donor> donors = dao.findAvailableDonors();
            List<DonorDTO> result = donors.stream()
                    .map(this::toDTOWithEligibility)
                    .collect(Collectors.toList());

            logMethodExit("getAvailableDonors", result.size() + " found");
            return result;
        } catch (Exception e) {
            logError("Failed to get available donors", e);
            throw new RuntimeException("Failed to get available donors", e);
        }
    }

    @Override
    public Optional<DonorDTO> getEligibleDonors() {
        logMethodEntry("getEligibleDonors");

        try {
            List<Donor> donors = dao.findEligibleDonors();

            Optional<DonorDTO> result = donors.stream()
                    .findFirst()
                    .map(this::toDTOWithEligibility);

            logMethodExit("getEligibleDonors", result.isPresent() ? "Found" : "None found");
            return result;
        } catch (Exception e) {
            logError("Failed to get eligible donors", e);
            throw new RuntimeException("Failed to get eligible donors", e);
        }
    }

    @Override
    public List<DonorDTO> getDonorsByBloodType(BloodType bloodType) {
        logMethodEntry("getDonorsByBloodType", bloodType);

        try {
            List<Donor> donors = dao.findByBloodType(bloodType);
            List<DonorDTO> result = donors.stream()
                    .map(this::toDTOWithEligibility)
                    .collect(Collectors.toList());

            logMethodExit("getDonorsByBloodType", result.size() + " found");
            return result;
        } catch (Exception e) {
            logError("Failed to get donors by blood type", e);
            throw new RuntimeException("Failed to get donors by blood type", e);
        }
    }

    @Override
    public boolean isCinUnique(String cin) {
        logMethodEntry("isCinUnique", cin);

        try {
            boolean unique = !dao.existsByCin(cin);
            logMethodExit("isCinUnique", unique);
            return unique;
        } catch (Exception e) {
            logError("Failed to check CIN uniqueness", e);
            throw new RuntimeException("Failed to check CIN uniqueness", e);
        }
    }

    @Override
    public Optional<DonorDTO> getDonorByCin(String cin) {
        logMethodEntry("getDonorByCin", cin);

        try {
            Optional<Donor> donorOpt = dao.findByCin(cin);
            Optional<DonorDTO> result = donorOpt.map(this::toDTOWithEligibility);

            logMethodExit("getDonorByCin", result.isPresent() ? "Found" : "Not Found");
            return result;
        } catch (Exception e) {
            logError("Failed to get donor by CIN", e);
            throw new RuntimeException("Failed to get donor by CIN", e);
        }
    }

    // Helper method to convert entity to DTO and add eligibility info
    public DonorDTO toDTOWithEligibility(Donor donor) {
        DonorDTO dto = mapper.toDto(donor);
        dto.setEligible(isEligible(donor));
        dto.setCanDonate(canDonate(donor));
        return dto;
    }
}
