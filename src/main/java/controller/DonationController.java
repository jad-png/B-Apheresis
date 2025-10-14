package controller;

import dto.DonationDTO;
import dto.DonorDTO;
import service.impl.DonationServiceImpl;
import service.interfaces.DonationService;
import utils.Loggable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DonationController extends Loggable {
    private final DonationService service;

    public DonationController(DonationService service) {
        this.service = service;
    }

    public DonationDTO createDonation(DonationDTO dto) {
        logMethodEntry("createDonation", dto);
        try {
            DonationDTO result = service.saveDonation(dto);
            logMethodExit("createDonation", result);
            return result;
        } catch (Exception e) {
            logError("Error in createDonation", e);
            throw new RuntimeException("Failed to create donation", e);
        }
    }

    public void deleteDonation(Long id) {
        logMethodEntry("deleteDonation", id);
        try {
            service.deleteDonation(id);
        }  catch (Exception e) {
            logError("Error in deleteDonation", e);
        }
    }

    public Optional<DonationDTO> getDonationById(Long id) {
        logMethodEntry("getDonationById", id);
        try {
            Optional<DonationDTO> result = service.getDonation(id);
            logMethodExit("getDonationById", result);
            return result;
        }  catch (Exception e) {
            logError("Error in getDonationById", e);
            throw new RuntimeException("Failed to get donation", e);
        }
    }

    public List<DonationDTO> getAllDonations() {
        logMethodEntry("getAllDonations");
        try {
            List<DonationDTO> result = service.getAllDonations();
            logMethodExit("getAllDonations", result);
            return result;
        } catch (Exception e) {
            logError("Error in getAllDonations", e);
            throw new RuntimeException("Failed to get donations", e);
        }
    }

    public List<DonationDTO> getDonnationByDonorId(Long id) {
        logMethodEntry("getDonationByDonorId", id);
        try {
            List<DonationDTO> result = service.getByDonatorId(id);
            logMethodExit("getDonationByDonorId", result);
            return result;
        } catch (Exception e) {
            logError("Error in getDonationByDonorId", e);
            throw new RuntimeException("Failed to get donators", e);
        }
    }

    public List<DonationDTO> getDonationsByRecipientId (Long id) {
        logMethodEntry("getDonationsByRecipientId", id);
        try {
            List<DonationDTO> result = service.getByRecipientId(id);
            logMethodExit("getDonationsByRecipientId", result);
            return result;
        }  catch (Exception e) {
            logError("Error in getDonationsByRecipientId", e);
            throw new RuntimeException("Failed to get donators", e);
        }
    }
}
