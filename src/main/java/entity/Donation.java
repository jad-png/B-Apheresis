package entity;

import javax.persistence.*;

@Entity
@Table(name = "donations")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "donator_id")
    private Account donor;

    @ManyToOne
    @JoinColumn(name = "recipient_id")
    private Account recipient;

    public Donation(Account donor, Account recipient) {
        this.donor = donor;
        this.recipient = recipient;
    }

    public Donation() {}


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Account getDonor() { return donor; }
    public void setDonor(Account donor) { this.donor = donor; }

    public Account getRecipient() { return recipient; }
    public void setRecipient(Account recipient) { this.recipient = recipient; }
}
