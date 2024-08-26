package orphans.management.system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orphans")
public class Orphan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orphanId;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String dateOfBirth;
    private String gender;
    private String address;
    private String orphanType;
    private String GurdianHouse;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "houseId")
    private House house;
}
