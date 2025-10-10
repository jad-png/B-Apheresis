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

    // busineess
    public void calculateRequiredBags() {
        switch (mcSituation) {
            case CRITICAL:
                this.requiredBags = 4;
                break;
            case URGENT:
                this.requiredBags = 3;
                break;
            case NORMAL:
                this.requiredBags = 1;
                break;
            default:
                this.requiredBags = 1;
        }
        updateState();
    }

    public boolean isSatisfied() {
        return currentBags >= requiredBags;
    }

    public Integer getRemainingBags() {
        return Math.max(0, requiredBags - currentBags);
    }

    public int getPriorityLevel() {
        switch (mcSituation) {
            case CRITICAL: return 3;
            case URGENT: return 2;
            case NORMAL: return 1;
            default: return 0;
        }
    }

    public void addDonor(Donor donor) {
        if (state != State.SATISFIED && donors.size() < requiredBags) {
            donors.add(donor);
            donor.setRecipient(this);
            currentBags = donors.size();
            updateState();
        }
    }

    public void removeDonor(Donor donor) {
        if (donors.remove(donor)) {
            donor.setRecipient(null);
            currentBags = donors.size();
            updateState();
        }
    }
    
    public void updateState() {
        this.state = isSatisfied() ? State.SATISFIED : State.WAITING;
    }

    // getters/setters
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
