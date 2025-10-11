package service.impl;

import dao.interfaces.DonorDao;
import dao.interfaces.RecipientDao;
import dto.DonorDTO;
import entity.enums.BloodType;

public class MatchingService {
    private final DonorDao donorDao;
    private final RecipientDao recipientDao;

    public MatchingService(DonorDao donorDao, RecipientDao recipientDao) {
        this.donorDao = donorDao;
        this.recipientDao = recipientDao;
    }

    private boolean isCompatible(BloodType donor, BloodType recipient) {
        switch (donor) {
            case O_NEG: return true;
            case O_POS: return recipient != BloodType.AB_NEG && recipient != BloodType.B_NEG && recipient != BloodType.A_NEG;
            case A_NEG: return recipient == BloodType.A_NEG || recipient == BloodType.A_POS || recipient == BloodType.AB_NEG || recipient == BloodType.AB_POS;
            case A_POS: return recipient == BloodType.A_POS || recipient == BloodType.AB_POS;
            case B_NEG: return recipient == BloodType.B_NEG || recipient == BloodType.B_POS || recipient == BloodType.AB_NEG || recipient == BloodType.AB_POS;
            case B_POS: return recipient == BloodType.B_POS || recipient == BloodType.AB_POS;
            case AB_NEG: return recipient == BloodType.AB_NEG || recipient == BloodType.AB_POS;
            case AB_POS: return recipient == BloodType.AB_POS;
            default: return false;
        }
    }
}
