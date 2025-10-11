package controller;

import dto.RecipientDTO;
import entity.enums.BloodType;
import entity.enums.Situation;
import entity.enums.State;
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

    public List<RecipientDTO> getRecipientsByBloodType(String bloodType) {
        logMethodEntry("getRecipientsByBloodType", bloodType);
        try {
            BloodType bt = BloodType.valueOf(bloodType.toUpperCase());
            List<RecipientDTO> result = service.getRecipientsByBloodType(bt);
            logMethodExit("getRecipientsByBloodType", result.size() + " recipients found");
            return result;
        } catch (IllegalArgumentException e) {
            logError("Invalid blood type: " + bloodType, e);
            throw new RuntimeException("Invalid blood type: " + bloodType);
        } catch (Exception e) {
            logError("Error in getRecipientsByBloodType controller", e);
            throw new RuntimeException("Failed to retrieve recipients", e);
        }
    }

    public List<RecipientDTO> getWaitingRecipients() {
        logMethodEntry("getWaitingRecipients");
        try {
            List<RecipientDTO> result = service.getWaitingRecipients();
            logMethodExit("getWaitingRecipients", result.size() + " recipients found");
            return result;
        } catch (Exception e) {
            logError("Error in getWaitingRecipients controller", e);
            throw new RuntimeException("Failed to retrieve waiting recipients", e);
        }
    }

    public List<RecipientDTO> getRecipientsByState(String state) {
        logMethodEntry("getRecipientsByState", state);
        try {
            State parsedState = State.valueOf(state.toUpperCase());
            List<RecipientDTO> result = service.getRecipientByState(parsedState);
            logMethodExit("getRecipientsByState", result.size() + " recipients found");
            return result;
        } catch (IllegalArgumentException e) {
            logError("Invalid state: " + state, e);
            throw new RuntimeException("Invalid recipient state: " + state);
        } catch (Exception e) {
            logError("Error in getRecipientsByState controller", e);
            throw new RuntimeException("Failed to retrieve recipients by state", e);
        }
    }

    public List<RecipientDTO> getRecipientsBySituation(String situation) {
        logMethodEntry("getRecipientsBySituation", situation);
        try {
            Situation parsedSituation = Situation.valueOf(situation.toUpperCase());
            List<RecipientDTO> result = service.getRecipientBySituation(parsedSituation);
            logMethodExit("getRecipientsBySituation", result.size() + " recipients found");
            return result;
        } catch (IllegalArgumentException e) {
            logError("Invalid situation: " + situation, e);
            throw new RuntimeException("Invalid recipient situation: " + situation);
        } catch (Exception e) {
            logError("Error in getRecipientsBySituation controller", e);
            throw new RuntimeException("Failed to retrieve recipients by situation", e);
        }
    }

    public List<RecipientDTO> getRecipientsByPriority() {
        logMethodEntry("getRecipientsByPriority");
        try {
            List<RecipientDTO> result = service.getRecipientsByPriority();
            logMethodExit("getRecipientsByPriority", result.size() + " recipients found");
            return result;
        } catch (Exception e) {
            logError("Error in getRecipientsByPriority controller", e);
            throw new RuntimeException("Failed to retrieve prioritized recipients", e);
        }
    }

    public long countByState(String state) {
        logMethodEntry("countByState", state);
        try {
            State parsedState = State.valueOf(state.toUpperCase());
            long count = service.countByState(parsedState);
            logMethodExit("countByState", count);
            return count;
        } catch (Exception e) {
            logError("Error in countByState controller", e);
            throw new RuntimeException("Failed to count recipients by state", e);
        }
    }
}
