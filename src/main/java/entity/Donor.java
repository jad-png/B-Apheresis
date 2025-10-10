package entity;

import entity.enums.BloodType;
import entity.enums.Gender;
import entity.enums.MedicalCondition;
import entity.enums.Status;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "donors")
@PrimaryKeyJoinColumn(name = "account_id")
public class Donor extends Account {
    @Column(name = "weight")
    private Double weight;

    // enum status
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "last_donation_date")
    @Temporal(TemporalType.DATE)
    private Date lastDonationDate;

    // medical condition enum
    @Enumerated(EnumType.STRING)
    @Column(name = "medical_condition")
    private MedicalCondition mdCondition;

    // TODO: create recipient class and type this attribute with it
    // many donors can donate to one recipient relation
    @ManyToOne
    // recipient fk
    @JoinColumn(name = "recipient_id")
    private Recipient recipient;

    public Donor() {
        super();
        this.status = Status.AVAILABLE;
    }

    public Donor(String firstName, String lastName, String cin, String phone,
                 Date birthday, BloodType bloodType, Gender gender,
                 Double weight, MedicalCondition medicalCondition) {
        super(firstName, lastName, cin, birthday, gender, bloodType);
        this.weight = weight;
        this.mdCondition = medicalCondition;
        this.status = Status.AVAILABLE;
    }

    // Business
    public boolean isEligible() {
        int age = calculateAge();
        boolean ageValid = age >= 18 && age <= 65;
        boolean weightValid = weight != null && weight >= 50.0;
        boolean medicalValid = mdCondition == MedicalCondition.HEALTHY;

        return ageValid && weightValid && medicalValid;
    }

    public boolean canDonate() {
        return isEligible() && status == Status.AVAILABLE;
    }

    public void updateStatus() {
        if (!isEligible()) {
            this.status = Status.INELIGIBLE;
        } else if (recipient != null) {
            this.status = Status.UNAVAILABLE;
        } else {
            this.status = Status.AVAILABLE;
        }
    }

    // getteres/setters
    public Status getStatus() { return status; }
    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getLastDonationDate() { return lastDonationDate; }
    public void setLastDonationDate(Date lastDonationDate) {

        this.lastDonationDate = lastDonationDate;
    }

    public MedicalCondition getMedicalCondition() { return mdCondition; }
    public void setMedicalCondition(MedicalCondition medicalCondition) {
        this.mdCondition = medicalCondition;
        updateStatus();
    }

    public Recipient getRecipient() { return recipient; }
    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }
}
