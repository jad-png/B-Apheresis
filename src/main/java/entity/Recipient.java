package entity;

import entity.enums.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "recipients")
@PrimaryKeyJoinColumn(name = "account_id")
public class Recipient extends Account {

    @Column(name = "required_bags")
    private Integer requiredBags;

    @Column(name = "current_bags")
    private Integer currentBags;

    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private State state;

    @Enumerated(EnumType.STRING)
    @Column(name = "medical_situation")
    private Situation mcSituation;

    @OneToMany(mappedBy = "recipient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Donor> donors;

    public Recipient() {
        super();
        this.currentBags = 0;
        this.state = State.WAITING;
    }

    public Recipient(String firstName, String lastName, String cin, String phone,
                     Date birthday, BloodType bloodType, Gender gender,
                     Double weight, Situation mcSituation) {
        super(firstName, lastName, cin, birthday, gender, bloodType);
        this.mcSituation = mcSituation;
        this.currentBags = 0;
        this.state = state.WAITING;
        this.requiredBags = 0;
    }

    public Integer getRequiredBags() {
        return requiredBags;
    }

    public void setRequiredBags(Integer requiredBags) {
        this.requiredBags = requiredBags;
    }

    public Integer getCurrentBags() {
        return currentBags;
    }

    public void setCurrentBags(Integer currentBags) {
        this.currentBags = currentBags;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Situation getMcSituation() {
        return mcSituation;
    }

    public void setMcSituation(Situation mcSituation) {
        this.mcSituation = mcSituation;
    }

    public List<Donor> getDonors() {
        return donors;
    }

    public void setDonors(List<Donor> donors) {
        this.donors = donors;
    }
}
