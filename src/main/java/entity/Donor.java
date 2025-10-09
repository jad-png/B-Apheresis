package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "donors")
@PrimaryKeyJoinColumn(name = "account_id")
public class Donor extends Account {
    @Column(name = "weight")
    private Double weight;

    // enum status

    @Column(name = "last_donation_date")
    @Temporal(TemporalType.DATE)
    private Date lastDonationDate;

    // medical condition enum

    // many donors can donate to one recipient relation
    // @JoinColumn(name = "revipient_id")
    // recipient fk

    public Donor() {
        super();
        // define default status
    }
}
