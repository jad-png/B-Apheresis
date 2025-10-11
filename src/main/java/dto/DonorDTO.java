package dto;

import entity.enums.BloodType;
import entity.enums.Gender;
import entity.enums.MedicalCondition;
import entity.enums.Status;

import java.util.Date;

public class DonorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String cin;
    private String phone;
    private Date birthday;
    private BloodType bloodType;
    private Gender gender;
    private Double weight;
    private Status status;
    private Date lastDonationDate;
    private MedicalCondition medicalCondition;
    private Date createdAt;
    private Integer age;
    private String fullName;
    private Boolean eligible;
    private Boolean canDonate;

    public DonorDTO() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getCin()  { return cin; }
    public void setCin(String cin) { this.cin = cin; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Date getBirthday() { return birthday; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }

    public BloodType getBloodType() { return bloodType; }
    public void setBloodType(BloodType bloodType) { this.bloodType = bloodType; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public Date getLastDonationDate() { return lastDonationDate; }
    public void setLastDonationDate(Date lastDonationDate) { this.lastDonationDate = lastDonationDate; }

    public MedicalCondition getMedicalCondition() { return medicalCondition; }
    public void setMedicalCondition(MedicalCondition medicalCondition) { this.medicalCondition = medicalCondition; }

    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public Boolean getEligible() { return eligible; }
    public void setEligible(Boolean eligible) { this.eligible = eligible; }

    public Boolean getCanDonate() { return canDonate; }
    public void setCanDonate(Boolean canDonate) { this.canDonate = canDonate; }
}
