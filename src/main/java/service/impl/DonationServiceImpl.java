package service.impl;

import dao.impl.DonationDaoImpl;
import dto.DonationDTO;
import mapper.DonationMapper;
import service.interfaces.DonationService;
import utils.Loggable;

import java.util.Collections;
import java.util.List;

public class DonationServiceImpl extends Loggable implements DonationService {
    private final DonationDaoImpl dao;
    private final DonationMapper mapper;

    public DonationServiceImpl(DonationDaoImpl dao, DonationMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }


    @Override
    public DonationDTO saveDonation(DonationDTO dto) {
        return null;
    }

    @Override
    public void deleteDonation(Long id) {

    }

    @Override
    public List<DonationDTO> getDonation(Long id) {
        return Collections.emptyList();
    }

    @Override
    public List<DonationDTO> getAllDonations() {
        return Collections.emptyList();
    }

    @Override
    public List<DonationDTO> getByDonatorId(Long donatorId) {
        return Collections.emptyList();
    }

    @Override
    public List<DonationDTO> getByRecipientId(Long id) {
        return Collections.emptyList();
    }
}
