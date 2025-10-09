package entity;

import entity.enums.BloodType;
import entity.enums.Gender;
import utils.Loggable;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "accounts")
abstract class Account extends Loggable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "cin", unique = true, nullable = false)
    private String cin;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_type", nullable = false)
    private BloodType blod_type;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "birthday", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    public Account() {
        this.created_at = new Date();
    }

    public Account(String firstName, String lastName, String cin, Date birthday, Gender gender, BloodType bloodType) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.cin = cin;
        this.birthday = birthday;
        this.gender = gender;
        this.blod_type = bloodType;
    }

    // busines methods
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int calculateAge() {
        LocalDate birthDate = birthday.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        LocalDate currentDate = LocalDate.now();

        return Period.between(birthDate, currentDate).getYears();
    }

    public boolean validatePersonalInfo() {
        return firstName != null && !firstName.trim().isEmpty() &&
                lastName != null && !lastName.trim().isEmpty() &&
                cin != null && !cin.trim().isEmpty() &&
                birthday != null;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }

    public Date getBirthday() { return birthday; }
    public void setBirthday(Date birthday) { this.birthday = birthday; }

    public BloodType getBloodType() { return blod_type; }
    public void setBloodType(BloodType bloodType) { this.blod_type = bloodType; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public Date getCreatedAt() { return created_at; }
    public void setCreatedAt(Date createdAt) { this.created_at = createdAt; }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", cin='" + cin + '\'' +
                ", bloodType=" + blod_type +
                '}';
    }
}
