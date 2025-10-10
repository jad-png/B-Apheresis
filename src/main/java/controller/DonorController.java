package controller;

import dto.DonorDTO;
import entity.enums.BloodType;
import service.impl.DonorServiceImpl;
import utils.Loggable;

import java.util.List;
import java.util.Optional;

public class DonorController extends Loggable {
    private final DonorServiceImpl donorSer;

    public DonorController(DonorServiceImpl donorSer) {
        this.donorSer = donorSer;
    }

    public DonorDTO createDonor(DonorDTO dto) {
        logMethodEntry("createDonor", dto);
        try {
            DonorDTO result = donorSer.createDonor(dto);
            logMethodExit("createDonor", result);
            return result;
        } catch (Exception e) {
            logError("Error in createDonor controller", e);
            throw e;
        }
    }

    public Optional<DonorDTO> getDonor(long id) {
        logMethodEntry("getDonor", id);
        try {
            Optional<DonorDTO> result = donorSer.getDonorById(id);
            logMethodExit("getDonor", result);
            return result;
        }  catch (Exception e) {
            logError("Error in getDonor controller", e);
            return Optional.empty();
        }
    }

    public List<DonorDTO> getAllDonors() {
        logMethodEntry("getAllDonors");
        try {
            List<DonorDTO> result = donorSer.getAllDonors();
            logMethodExit("getAllDonors", result);
            return result;
        }  catch (Exception e) {
            logError("Error in getAllDonor controller", e);
            throw new RuntimeException("Failed to retrieve donors", e);
        }

    }

    public List<DonorDTO> getAvailableDonors() {
        logMethodEntry("getAvailableDonors");
        try {
            List<DonorDTO> result = donorSer.getAvailableDonors();
            logMethodExit("getAvailableDonors", result.size() + " available donors retrieved");
            return result;
        } catch (Exception e) {
            logError("Error in getAvailableDonors controller", e);
            throw new RuntimeException("Failed to retrieve available donors", e);
        }
    }

    public List<DonorDTO> getDonorsByBloodType(String bloodType) {
        logMethodEntry("getDonorsByBloodType", bloodType);
        try {
            BloodType type = BloodType.valueOf(bloodType.toUpperCase());
            List<DonorDTO> result = donorSer.getDonorsByBloodType(type);
            logMethodExit("getDonorsByBloodType", result.size() + " donors retrieved");
            return result;
        } catch (IllegalArgumentException e) {
            logError("Invalid blood type: " + bloodType, e);
            throw new RuntimeException("Invalid blood type: " + bloodType);
        } catch (Exception e) {
            logError("Error in getDonorsByBloodType controller", e);
            throw new RuntimeException("Failed to retrieve donors by blood type", e);
        }
    }

    public DonorDTO updateDonor(DonorDTO dto) {
        logMethodEntry("updateDonor", dto);
        try {
            DonorDTO result = donorSer.updateDonor(dto);
            logMethodExit("updateDonor", result);
            return result;
        } catch (Exception e) {
            logError("Error in updateDonor controller", e);
            throw e;
        }
    }

    public boolean deleteDonor(Long id) {
        logMethodEntry("deleteDonor", id);
        try {
            donorSer.deleteDonor(id);
            logMethodExit("deleteDonor", true);
            return true;
        } catch (Exception e) {
            logError("Error in deleteDonor controller", e);
            return false;
        }
    }

    public boolean validateCin(String cin) {
        logMethodEntry("validateCin", cin);
        try {
            boolean isValid = donorSer.isCinUnique(cin);
            logMethodExit("validateCin", isValid);
            return isValid;
        } catch (Exception e) {
            logError("Error in validateCin controller", e);
            return false;
        }
    }
}
