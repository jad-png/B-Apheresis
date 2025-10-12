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
    @JoinColumn(name = "reviewer_id")
    private Account recipient;

    public Donation(Account donor, Account recipient) {
        this.donor = donor;
        this.recipient = recipient;
    }

    public Donation() {}

}
