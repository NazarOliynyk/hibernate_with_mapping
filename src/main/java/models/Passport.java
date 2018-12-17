package models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="passport_table")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = "user")
@NoArgsConstructor
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String series;
    private String number;
    @OneToOne(
            mappedBy = "passport", // it used to be in the class User before
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private User user;

    public Passport(String series, String number) {
        this.series = series;
        this.number = number;
    }

    public Passport(String series, String number, User user) {
        this.series = series;
        this.number = number;
        this.user = user;
    }
}
