package controller;

import dto.RecipientDTO;
import service.impl.RecipientServiceImpl;
import utils.Loggable;

import java.util.List;
import java.util.Optional;

public class RecipientController extends Loggable {
    private final RecipientServiceImpl service;

    public RecipientController(RecipientServiceImpl service) {
        this.service = service;
    }

    public RecipientDTO createRecipient(RecipientDTO dto) {
        logMethodEntry("createRecipient", dto);
        try {
            RecipientDTO result = service.saveRecipient(dto);
            logMethodExit("createRecipient", result);
            return result;
        } catch (Exception e) {
            logError("Error in createRecipient controller", e);
            throw e;
        }
    }

    public RecipientDTO updateRecipient(RecipientDTO dto) {
        logMethodEntry("updateRecipient", dto);
        try {
            RecipientDTO result = service.updateRecipient(dto);
            logMethodExit("updateRecipient", result);
            return result;
        } catch (Exception e) {
            logError("Error in updateRecipient controller", e);
            throw e;
        }
    }

    public boolean deleteRecipient(Long id) {
        logMethodEntry("deleteRecipient", id);
        try {
            service.deleteRecipient(id);
            logMethodExit("deleteRecipient", true);
            return true;
        } catch (Exception e) {
            logError("Error in deleteRecipient controller", e);
            return false;
        }
    }

    public Optional<RecipientDTO> getRecipientById(Long id) {
        logMethodEntry("getRecipientById", id);
        try {
            Optional<RecipientDTO> result = service.getRecipient(id);
            logMethodExit("getRecipientById", result);
            return result;
        } catch (Exception e) {
            logError("Error in getRecipientById controller", e);
            return Optional.empty();
        }
    }

    public List<RecipientDTO> getAllRecipients() {
        logMethodEntry("getAllRecipients");
        try {
            List<RecipientDTO> result = service.getAllRecipients();
            logMethodExit("getAllRecipients", result.size() + " recipients found");
            return result;
        } catch (Exception e) {
            logError("Error in getAllRecipients controller", e);
            throw new RuntimeException("Failed to retrieve recipients", e);
        }
    }
}
