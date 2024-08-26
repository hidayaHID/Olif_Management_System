package orphans.management.system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "donations")
public class Donation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long donationId;
    private String description;
    private long amount;
    private String donationType;
    private Date donatedDate;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private User donor;
}
