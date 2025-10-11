package service.impl;

import dao.interfaces.DonorDao;
import dao.interfaces.RecipientDao;
import dto.DonorDTO;

public class MatchingService {
    private final DonorDao donorDao;
    private final RecipientDao recipientDao;

    public MatchingService(DonorDao donorDao, RecipientDao recipientDao) {
        this.donorDao = donorDao;
        this.recipientDao = recipientDao;
    }
}
