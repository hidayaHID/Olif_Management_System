package orphans.management.system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import orphans.management.system.model.Donation;
import orphans.management.system.model.House;
import orphans.management.system.repository.HouseRepository;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class HouseService {
    private final HouseRepository houseRepository;
    private final DonationService donationService;


    public House registerNewHouse(House house) {
        house.setStatus("Pending");
        log.info("New house registered.");
        return houseRepository.save(house);
    }


    public House assignHouseToDonation(long houseId, long donationId) {
        House house = findHouseByHouseId(houseId);
        Donation donation = donationService.findDonationByDonationId(donationId);
        house.assignHouseToDonation(donation);
        house.setStatus("Received");
        log.info("House has been successfully donated.");
        return houseRepository.save(house);
    }




    public List<House> getAllHouses() {
        log.info("Houses retrieved.");
        return houseRepository.findAll();
    }


    public House updateHouse(String houseNumber, String guardianHouse, String address, double latitude, double longitude, String status, long houseId) {
        House updateHouse = findHouseByHouseId(houseId);
        updateHouse.setHouseNumber(houseNumber);
        updateHouse.setGuardianHouse(guardianHouse);
        updateHouse.setAddress(address);
        updateHouse.setLatitude(latitude);
        updateHouse.setLongitude(longitude);
        updateHouse.setStatus(status);
        log.info("House updated successfully.");
        return houseRepository.save(updateHouse);
    }

    public House findHouseByHouseId(long houseId) {
        House house = houseRepository.findByHouseId(houseId);
        if (house == null) {
            throw new RuntimeException("House not found");
        }

        return house;
    }


    public void deleteHouse(long houseId) {
        House house = houseRepository.findByHouseId(houseId);
        if (house == null) {
            throw new RuntimeException("House not found");
        }

        houseRepository.deleteByHouseId(houseId);
    }


    public Integer getTotalHouses() {
        return houseRepository.getTotalHouses();
    }

}
