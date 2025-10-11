package service.impl;

import dao.interfaces.RecipientDao;
import mapper.RecipientMapper;

public class RecipientServiceImpl {
    private final RecipientDao dao;
    private final RecipientMapper mapper;

    public RecipientServiceImpl(RecipientDao dao, RecipientMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    
}
