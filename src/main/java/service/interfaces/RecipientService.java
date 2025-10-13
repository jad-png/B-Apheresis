package service.interfaces;

import dto.RecipientDTO;
import entity.enums.BloodType;
import entity.enums.Situation;
import entity.enums.State;

import java.util.List;
import java.util.Optional;

public interface RecipientService {

    // basic crud methods
    RecipientDTO saveRecipient(RecipientDTO dto);
    RecipientDTO updateRecipient(RecipientDTO dto);
    boolean deleteRecipient(Long id);
    Optional<RecipientDTO> getRecipient(Long id);
    List<RecipientDTO> getAllRecipients();


    List<RecipientDTO> getRecipientByState(State state);
    List<RecipientDTO> getRecipientBySituation(Situation situation);
    List<RecipientDTO> getWaitingRecipients();
    List<RecipientDTO> getRecipientsByBloodType(BloodType bloodType);
    List<RecipientDTO> getRecipientsByPriority();
    List<RecipientDTO> getUnsatisfiedRecipients();

    boolean existsByCin(String cin);
    Long countByState(State state);
}
