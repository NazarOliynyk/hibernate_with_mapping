package models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
@Entity
@Table(name = "email_table")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"user", "passport"})
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String login;
    private String password;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private User user;

    public Email(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Email(String login, String password, User user) {
        this.login = login;
        this.password = password;
        this.user = user;
    }
}
