package dao.interfaces;

import entity.Recipient;
import entity.enums.BloodType;
import entity.enums.Situation;
import entity.enums.State;

import java.util.List;
import java.util.Optional;

public interface RecipientDao {

    // Base Crud methods
    Recipient save(Recipient rec);
    Recipient update(Recipient rec);
    void delete(Long id);
    Optional<Recipient> findById(Long id);
    List<Recipient> findAll();

    // business specific queries
    List<Recipient> findByBloodType(BloodType bloodType);
    List<Recipient> findWaitingRecipients();
    List<Recipient> findByPriority(); // listing by priority :// CRI > URG > NOR

    // advanced queries
    List<Recipient> findByState(State state);
    List<Recipient> findBySituation(Situation situation);
    List<Recipient> findInsatisfiedRecipients();

    // TODO: parameter donor blood type
    List<Recipient> findCompatibleRecipients();
    boolean existsByCin(String cin);
    Long CountByState(State state);
}
