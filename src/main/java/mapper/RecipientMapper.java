package mapper;

import dto.RecipientDTO;
import entity.Recipient;

public class RecipientMapper {

    public RecipientDTO toDto (Recipient rec) {
        if (rec == null) return null;

        RecipientDTO dto = new RecipientDTO();
        dto.setId(rec.getId());
        dto.setFistName(rec.getFirstName());
        dto.setLastName(rec.getLastName());
        dto.setCin(rec.getCin());
        dto.setBirthday(rec.getBirthday());
        dto.setBloodType(rec.getBloodType());
        dto.setGender(rec.getGender());

        dto.setRequiredBags(rec.getRequiredBags());
        dto.setCurrentBags(rec.getCurrentBags());
        dto.setState(rec.getState());
        dto.setSituation(rec.getSituation());

        dto.setFullName(rec.getFirstName() + " " + rec.getLastName());

        return dto;
    }

    public Recipient toEntity (RecipientDTO dto) {
        if (dto == null) return null;

        Recipient rec = new Recipient();
        rec.setId(dto.getId());
        rec.setFirstName(dto.getFistName());
        rec.setLastName(dto.getLastName());
        rec.setCin(dto.getCin());
        rec.setBirthday(dto.getBirthday());
        rec.setBloodType(dto.getBloodType());
        rec.setGender(dto.getGender());

        rec.setRequiredBags(dto.getRequiredBags());
        rec.setCurrentBags(dto.getCurrentBags());
        rec.setState(dto.getState());
        rec.setSituation(dto.getSituation());
        
        return rec;
    }
}
