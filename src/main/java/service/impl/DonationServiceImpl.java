package service.impl;

import dao.impl.DonationDaoImpl;
import dao.interfaces.DonationDao;
import dto.DonationDTO;
import entity.Donation;
import mapper.DonationMapper;
import service.interfaces.DonationService;
import utils.Loggable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DonationServiceImpl extends Loggable implements DonationService {
    private final DonationDao dao;
    private final DonationMapper mapper;

    public DonationServiceImpl(DonationDao dao, DonationMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }


    @Override
    public DonationDTO saveDonation(DonationDTO dto) {
        logMethodEntry("saveDonation", dto);
        Donation donation = mapper.toEntity(dto);
        Donation saved = dao.save(donation);
        DonationDTO result = mapper.toDto(saved);
        logMethodExit("saveDonation", result);
        return result;
    }

    @Override
    public void deleteDonation(Long id) {
        logMethodEntry("deleteDonation", id);
        dao.delete(id);
        logMethodExit("deleteDonation", id);
    }

    @Override
    public Optional<DonationDTO> getDonation(Long id) {
        logMethodEntry("getDonation", id);
        Optional<DonationDTO> dto = dao.findById(id).map(mapper::toDto);
        logMethodExit("getDonation", dto);
        return dto;
    }

    @Override
    public List<DonationDTO> getAllDonations() {
        logMethodEntry("getAllDonations");
        List<DonationDTO> list = dao.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public List<DonationDTO> getByDonatorId(Long donatorId) {
        logMethodEntry("getByDonatorId", donatorId);
        List<DonationDTO> list = dao.findByDonationId(donatorId).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public List<DonationDTO> getByRecipientId(Long id) {
        logMethodEntry("getByRecipientId", id);
        List<DonationDTO> list = dao.findByRecipientId(id).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        return list;
    }
}
