package dao.interfaces;

import entity.Recipient;
import entity.enums.BloodType;
import entity.enums.Situation;
import entity.enums.State;

import java.util.List;

public interface RecipientDao {

    // Base Crud methods
    Recipient save(Recipient rec);
    Recipient update(Recipient rec);
    boolean delete(Recipient rec);
    Recipient findById(Long id);
    List<Recipient> findAll();

    // business specific queries
    List<Recipient> findByBloodType(BloodType bloodType);
    List<Recipient> findWaitingRecipients();
    List<Recipient> findByPriority(); // listing by priority :// CRI > URG > NOR
    
}
