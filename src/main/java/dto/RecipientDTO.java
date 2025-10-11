package dto;

import entity.enums.BloodType;
import entity.enums.Gender;
import entity.enums.MedicalCondition;
import entity.enums.State;

import java.util.Date;
import java.util.List;

public class RecipientDTO {
    private Long id;
    private String fistName;
    private String lastName;
    private String cin;
    private BloodType bloodType;
    private Gender gender;
    private Date birthday;
    private Integer requiredBags;
    private Integer currentBags;
    private State state;
    private MedicalCondition mdCondition;
    private List<DonorDTO> donors;

    public RecipientDTO() {}

    
}
