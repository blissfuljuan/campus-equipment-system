package edu.cit.laborada.johnjoseph.campusequipmentloan.model;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class UserLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

}
