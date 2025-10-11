package service.impl;

import dao.interfaces.RecipientDao;
import dto.RecipientDTO;
import mapper.RecipientMapper;
import service.interfaces.RecipientService;
import utils.Loggable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RecipientServiceImpl extends Loggable implements RecipientService {
    private final RecipientDao dao;
    private final RecipientMapper mapper;

    public RecipientServiceImpl(RecipientDao dao, RecipientMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }


    @Override
    public RecipientDTO saveRecipient(RecipientDTO dto) {
        return null;
    }

    @Override
    public RecipientDTO updateRecipient(RecipientDTO dto) {
        return null;
    }

    @Override
    public void deleteRecipient(Long id) {

    }

    @Override
    public Optional<RecipientDTO> getRecipient(Long id) {
        return Optional.empty();
    }

    @Override
    public List<RecipientDTO> getAllRecipients() {
        return Collections.emptyList();
    }
}
