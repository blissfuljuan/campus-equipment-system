package campusequipmentloan.model;

import jakarta.persistence.*;

@Entity
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @Column(unique = true)
    private String serialNumber;

    private boolean available = true;

}

