package orphans.management.system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long houseId;
    private String houseNumber;
    private String guardianHouse;
    private String address;
    private String location;
    private String status;
    private double latitude;
    private double longitude;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "house_donation",
            joinColumns = @JoinColumn(name = "house_id", referencedColumnName = "houseId"),
            inverseJoinColumns = @JoinColumn(name = "donation_id", referencedColumnName = "donationId")
    )

    private Set<Donation> donations;


    public void assignHouseToDonation(Donation donation) {
        this.donations.add(donation);
    }
}
