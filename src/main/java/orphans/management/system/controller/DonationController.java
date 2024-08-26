package orphans.management.system.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orphans.management.system.model.Donation;
import orphans.management.system.service.DonationService;

import java.util.List;

@RestController
@RequestMapping("/donations")
@RequiredArgsConstructor
public class DonationController {
    private final DonationService donationService;


    @PostMapping("/create/{donorId}")
    public ResponseEntity<Donation> createDonation(@RequestBody Donation donation, @PathVariable long donorId) {
        Donation newDonation = donationService.createDonation(donation, donorId);
        return new ResponseEntity<>(newDonation, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Donation>> getAllDonations() {
        List<Donation> newDonation = donationService.getAllDonation();
        return new ResponseEntity<>(newDonation, HttpStatus.OK);
    }


    @GetMapping("/all/username/{username}")
    public ResponseEntity<List<Donation>> getAllDonationsByUsername(@PathVariable String username) {
        List<Donation> newDonation = donationService.getAllDonationsByUsername(username);
        return new ResponseEntity<>(newDonation, HttpStatus.OK);
    }


    @PostMapping("/update/{donationId}")
    public ResponseEntity<Donation> updateDonation(@RequestBody Donation donation, @PathVariable long donationId) {
        Donation updateDonation = donationService.updateDonation(donation.getDescription(), donation.getDonationType(), donation.getDonatedDate(), donationId);
        return new ResponseEntity<>(updateDonation, HttpStatus.OK);
    }


    @GetMapping("/{donationId}")
    public ResponseEntity<Donation> findDonation(@PathVariable long donationId) {
        Donation updateDonation = donationService.findDonationByDonationId(donationId);
        return new ResponseEntity<>(updateDonation, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{donationId}")
    public ResponseEntity<?> deleteDonation(@PathVariable long donationId) {
        donationService.deleteDonation(donationId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
