package models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="aka_table")
@Getter
@Setter
@EqualsAndHashCode
@ToString(exclude = {"users"})
public class Aka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String value;

    @ManyToMany(cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        mappedBy = "akas")
    List<User> users = new ArrayList<User>();
}
