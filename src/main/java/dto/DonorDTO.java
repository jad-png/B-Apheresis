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
}
