package service.impl;

import dao.interfaces.DonorDao;
import dao.interfaces.RecipientDao;
import dto.DonorDTO;
import entity.Recipient;
import entity.enums.BloodType;

import java.util.ArrayList;
import java.util.List;

public class MatchingService {
    private final DonorDao donorDao;
    private final RecipientDao recipientDao;

    public MatchingService(DonorDao donorDao, RecipientDao recipientDao) {
        this.donorDao = donorDao;
        this.recipientDao = recipientDao;
    }

    public List<Recipient> findCompatibleRecipients(BloodType donorBloodType) {
        List<Recipient> waitingRecipients = recipientDao.findWaitingRecipients();
        List<Recipient> compatibleRecipients = new ArrayList<>();

        for (Recipient r : waitingRecipients) {
            if (isCompatible(donorBloodType, r.getBloodType()) && !r.isSatisfied()) {
                compatibleRecipients.add(r);
            }
        }

        compatibleRecipients.sort((r1, r2) -> Integer.compare(r2.getPriorityLevel(), r1.getPriorityLevel()));

        return compatibleRecipients;
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
