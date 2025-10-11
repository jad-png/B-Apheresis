package service.interfaces;

import dto.RecipientDTO;

import java.util.List;
import java.util.Optional;

public interface RecipientService {

    // basic crud methods
    RecipientDTO saveRecipient(RecipientDTO dto);
    RecipientDTO updateRecipient(RecipientDTO dto);
    void deleteRecipient(Long id);
    Optional<RecipientDTO> getRecipient(Long id);
    List<RecipientDTO> getAllRecipients();

    
}
