package models;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user_table")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"passport", "emails", "akas"})
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToOne(
            //mappedBy = "user", // in the passport_table there is a column user_id
            fetch = FetchType.LAZY, // EAGER gives the info about both user and passport after the call to a certain user
            cascade = CascadeType.ALL  // writes into DB all fields ----- MUST BE !!!
    )
    private Passport passport;

    @OneToMany(mappedBy = "user",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    private List<Email> emails = new ArrayList<Email>();

    @ManyToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private List<Aka> akas = new ArrayList<Aka>();

    public User(String name) {
        this.name = name;
    }

    public User(String name, Passport passport) {
        this.name = name;
        this.passport = passport;
    }
}
