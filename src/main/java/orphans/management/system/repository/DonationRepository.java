package orphans.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import orphans.management.system.model.Donation;
import orphans.management.system.model.Orphan;

import java.util.List;

@Repository
public interface DonationRepository extends JpaRepository<Donation, Long> {

    Donation findByDonationId(long donationId);

    void deleteByDonationId(long donationId);

    @Query("SELECT d FROM Donation d JOIN d.donor u WHERE u.username = ?1")
    List<Donation> getAllDonationsByUsername(String username);
}
