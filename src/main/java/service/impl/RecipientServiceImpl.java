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
        Recipient r = mapper.toEntity(dto);

        r.calculateRequiredBags();
        r.updateState();

        Recipient saved = dao.save(r);
        RecipientDTO recDto = mapper.toDto(saved);

        logMethodExit("saveRecipient", dto);
        return recDto;
    }

    @Override
    public RecipientDTO updateRecipient(RecipientDTO dto) {
        logMethodEntry("updateRecipient", dto);
        Recipient r = mapper.toEntity(dto);

        r.calculateRequiredBags();
        r.updateState();

        Recipient saved = dao.save(r);
        RecipientDTO recDto = mapper.toDto(saved);

        logMethodExit("updateRecipient", dto);
        return recDto;
    }

    @Override
    public void deleteRecipient(Long id) {
        logMethodEntry("deleteRecipient", id);
        dao.delete(id);
        logMethodExit("deleteRecipient", id);
    }

    @Override
    public Optional<RecipientDTO> getRecipient(Long id) {
        logMethodEntry("getRecipient", id);
        Optional<Recipient> recipient = dao.findById(id);
        Optional<RecipientDTO> dto = recipient.map(mapper::toDto);
        logMethodExit("getRecipient", recipient);
        return dto;
    }

    @Override
    public List<RecipientDTO> getAllRecipients() {
        logMethodEntry("getAllRecipients");

        List<RecipientDTO> list = dao.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        logMethodExit("getAllRecipients", list);
        return list;
    }

    @Override
    public List<RecipientDTO> getRecipientByState(State state) {
        return Collections.emptyList();
    }

    @Override
    public List<RecipientDTO> getRecipientBySituation(Situation situation) {
        logMethodEntry("getRecipientsByState", situation);
        return dao.findBySituation(situation).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipientDTO> getWaitingRecipients() {
        logMethodEntry("getWaitingRecipients");
        return dao.findWaitingRecipients().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipientDTO> getRecipientsByBloodType(BloodType bloodType) {
        logMethodEntry("getRecipientsByBloodType", bloodType);
        return dao.findByBloodType(bloodType).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipientDTO> getRecipientsByPriority() {
        logMethodEntry("getRecipientsByPriority");
        return dao.findByPriority().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecipientDTO> getUnsatisfiedRecipients() {
        logMethodEntry("getUnsatisfiedRecipients");
        return dao.findInsatisfiedRecipients().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByCin(String cin) {
        logMethodEntry("existsByCin", cin);
        boolean exists = dao.existsByCin(cin);
        logMethodExit("existsByCin", exists);
        return exists;
    }

    @Override
    public Long countByState(State state) {
        logMethodEntry("countByState", state);
        Long count = dao.CountByState(state);
        logMethodExit("countByState", count);
        return count;
    }
}
