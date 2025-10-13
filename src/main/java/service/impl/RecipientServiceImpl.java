package service.impl;

import dao.interfaces.RecipientDao;
import dto.RecipientDTO;
import entity.Recipient;
import entity.enums.BloodType;
import entity.enums.Situation;
import entity.enums.State;
import mapper.RecipientMapper;
import service.interfaces.RecipientService;
import utils.Loggable;

import javax.persistence.EntityManager;
import javax.sound.sampled.ReverbType;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class RecipientServiceImpl extends Loggable implements RecipientService {
    private final RecipientDao dao;
    private final RecipientMapper mapper;
    private final EntityManager em;

    public RecipientServiceImpl(RecipientDao dao, RecipientMapper mapper, EntityManager em) {
        this.dao = dao;
        this.mapper = mapper;
        this.em = em;
    }


    @Override
    public RecipientDTO saveRecipient(RecipientDTO dto) {
        logMethodEntry("saveRecipient", dto);
        try {
            em.getTransaction().begin();

            Recipient r = mapper.toEntity(dto);
            r.calculateRequiredBags();
            r.updateState();

            Recipient saved = dao.save(r);

            em.getTransaction().commit();

            RecipientDTO recDto = mapper.toDto(saved);
            logMethodExit("saveRecipient", recDto);
            return recDto;
        } catch (Exception e) {
            logError("saveRecipient", e);
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new RuntimeException("Error saving recipient", e);
        }
    }

    @Override
    public RecipientDTO updateRecipient(RecipientDTO dto) {
        logMethodEntry("updateRecipient", dto);
        try {
            em.getTransaction().begin();

            Recipient r = mapper.toEntity(dto);
            r.calculateRequiredBags();
            r.updateState();

            Recipient saved = dao.save(r);

            em.getTransaction().commit();

            RecipientDTO recDto = mapper.toDto(saved);
            logMethodExit("updateRecipient", recDto);
            return recDto;
        } catch (Exception e) {
            logError("updateRecipient", e);
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new RuntimeException("Error updating recipient", e);
        }
    }

    @Override
    public void deleteRecipient(Long id) {
        logMethodEntry("deleteRecipient", id);
        try {
            em.getTransaction().begin();

            dao.delete(id);

            em.getTransaction().commit();

            logMethodExit("deleteRecipient", id);
        } catch (Exception e) {
            logError("deleteRecipient", e);
            if (em.getTransaction().isActive()) em.getTransaction().rollback();
            throw new RuntimeException("Error deleting recipient", e);
        }
    }

    @Override
    public Optional<RecipientDTO> getRecipient(Long id) {
        logMethodEntry("getRecipient", id);
        try {
            Optional<Recipient> recipient = dao.findById(id);
            Optional<RecipientDTO> dto = recipient.map(mapper::toDto);
            logMethodExit("getRecipient", dto);
            return dto;
        } catch (Exception e) {
            logError("getRecipient", e);
            throw new RuntimeException("Error fetching recipient", e);
        }
    }

    @Override
    public List<RecipientDTO> getAllRecipients() {
        logMethodEntry("getAllRecipients");
        try {
            List<RecipientDTO> list = dao.findAll().stream()
                    .map(mapper::toDto)
                    .collect(Collectors.toList());
            logMethodExit("getAllRecipients", list);
            return list;
        } catch (Exception e) {
            logError("getAllRecipients", e);
            throw new RuntimeException("Error fetching all recipients", e);
        }
    }

    @Override
    public List<RecipientDTO> getRecipientByState(State state) {
        return Collections.emptyList();
    }

    @Override
    public List<RecipientDTO> getRecipientBySituation(Situation situation) {
        logMethodEntry("getRecipientsBySituation", situation);
        try {
            List<RecipientDTO> list = dao.findBySituation(situation).stream()
                    .map(mapper::toDto)
                    .collect(Collectors.toList());
            logMethodExit("getRecipientsBySituation", list);
            return list;
        } catch (Exception e) {
            logError("getRecipientsBySituation", e);
            throw new RuntimeException("Error fetching recipients by situation", e);
        }
    }

    @Override
    public List<RecipientDTO> getWaitingRecipients() {
        logMethodEntry("getWaitingRecipients");
        try {
            List<RecipientDTO> list = dao.findWaitingRecipients().stream()
                    .map(mapper::toDto)
                    .collect(Collectors.toList());
            logMethodExit("getWaitingRecipients", list);
            return list;
        } catch (Exception e) {
            logError("getWaitingRecipients", e);
            throw new RuntimeException("Error fetching waiting recipients", e);
        }
    }

    @Override
    public List<RecipientDTO> getRecipientsByBloodType(BloodType bloodType) {
        logMethodEntry("getRecipientsByBloodType", bloodType);
        try {
            List<RecipientDTO> list = dao.findByBloodType(bloodType).stream()
                    .map(mapper::toDto)
                    .collect(Collectors.toList());
            logMethodExit("getRecipientsByBloodType", list);
            return list;
        } catch (Exception e) {
            logError("getRecipientsByBloodType", e);
            throw new RuntimeException("Error fetching recipients by blood type", e);
        }
    }

    @Override
    public List<RecipientDTO> getRecipientsByPriority() {
        logMethodEntry("getRecipientsByPriority");
        try {
            List<RecipientDTO> list = dao.findByPriority().stream()
                    .map(mapper::toDto)
                    .collect(Collectors.toList());
            logMethodExit("getRecipientsByPriority", list);
            return list;
        } catch (Exception e) {
            logError("getRecipientsByPriority", e);
            throw new RuntimeException("Error fetching recipients by priority", e);
        }
    }

    @Override
    public List<RecipientDTO> getUnsatisfiedRecipients() {
        logMethodEntry("getUnsatisfiedRecipients");
        try {
            List<RecipientDTO> list = dao.findInsatisfiedRecipients().stream()
                    .map(mapper::toDto)
                    .collect(Collectors.toList());
            logMethodExit("getUnsatisfiedRecipients", list);
            return list;
        } catch (Exception e) {
            logError("getUnsatisfiedRecipients", e);
            throw new RuntimeException("Error fetching unsatisfied recipients", e);
        }
    }

    @Override
    public boolean existsByCin(String cin) {
        logMethodEntry("existsByCin", cin);
        try {
            boolean exists = dao.existsByCin(cin);
            logMethodExit("existsByCin", exists);
            return exists;
        } catch (Exception e) {
            logError("existsByCin", e);
            throw new RuntimeException("Error checking existence by CIN", e);
        }
    }

    @Override
    public Long countByState(State state) {
        logMethodEntry("countByState", state);
        try {
            Long count = dao.CountByState(state);
            logMethodExit("countByState", count);
            return count;
        } catch (Exception e) {
            logError("countByState", e);
            throw new RuntimeException("Error counting recipients by state", e);
        }
    }
}
