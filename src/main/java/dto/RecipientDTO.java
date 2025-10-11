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

    // parent class Account attributes
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFistName() { return fistName; }
    public void setFistName(String fistName) { this.fistName = fistName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }

    public BloodType getBloodType() { return bloodType; }
    public void setBloodType(BloodType bloodType) { this.bloodType = bloodType; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public Date getBirthday() { return birthday; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }

    // Recipient attributes
    public Integer getRequiredBags() { return requiredBags; }
    public void setRequiredBags(Integer requiredBags) { this.requiredBags = requiredBags; }

    public Integer getCurrentBags() { return currentBags; }
    public void setCurrentBags(Integer currentBags) { this.currentBags = currentBags; }

    public State getState() { return state; }
    public void setState(State state) { this.state = state; }

    public MedicalCondition getMdCondition() { return mdCondition; }
    public void setMdCondition(MedicalCondition mdCondition) { this.mdCondition = mdCondition; }

    public List<DonorDTO> getDonors() { return donors; }
}
