package service.impl;

import dao.impl.DonationDaoImpl;
import mapper.DonationMapper;

public class DonationServiceImpl {
    private final DonationDaoImpl dao;
    private final DonationMapper mapper;

    public DonationServiceImpl(DonationDaoImpl dao, DonationMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }


}
