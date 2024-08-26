package orphans.management.system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import orphans.management.system.model.Donation;
import orphans.management.system.model.User;
import orphans.management.system.repository.DonationRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DonationService {
    private final DonationRepository donationRepository;
    private final UserService userService;


    public Donation createDonation(Donation donation, long donorId) {
        User donor = userService.findUserByUserId(donorId);
        donation.setDonatedDate(new Date());
        donation.setDonor(donor);
        log.info("New donation created successfully.");
        return donationRepository.save(donation);
    }


    public List<Donation> getAllDonation() {
        log.info("Donations retrieved.");
        return donationRepository.findAll();
    }


    public List<Donation> getAllDonationsByUsername(String username) {
        log.info("Donations retrieved.");
        return donationRepository.getAllDonationsByUsername(username);
    }


    public Donation updateDonation(String description, String donationType, Date donatedDate, long donationId) {
        Donation updateDonation = findDonationByDonationId(donationId);
        updateDonation.setDescription(description);
        updateDonation.setDonationType(donationType);
        updateDonation.setDonatedDate(donatedDate);
        log.info("Donation updated successfully.");
        return donationRepository.save(updateDonation);
    }



    public Donation findDonationByDonationId(long donationId) {
        Donation donation = donationRepository.findByDonationId(donationId);
        if (donation == null) {
            throw new RuntimeException("Donation not found");
        }

        return donation;
    }


    public void deleteDonation(long donationId) {
        Donation donation = findDonationByDonationId(donationId);
        donationRepository.deleteByDonationId(donation.getDonationId());
    }

}
