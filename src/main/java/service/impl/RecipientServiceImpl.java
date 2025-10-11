package service.impl;

import dao.interfaces.RecipientDao;
import dto.RecipientDTO;
import entity.Recipient;
import mapper.RecipientMapper;
import service.interfaces.RecipientService;
import utils.Loggable;

import javax.sound.sampled.ReverbType;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RecipientServiceImpl extends Loggable implements RecipientService {
    private final RecipientDao dao;
    private final RecipientMapper mapper;

    public RecipientServiceImpl(RecipientDao dao, RecipientMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
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
}
