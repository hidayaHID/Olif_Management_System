package orphans.management.system.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orphans.management.system.model.Donation;
import orphans.management.system.model.House;
import orphans.management.system.service.HouseService;

import java.util.List;

@RestController
@RequestMapping("/houses")
@RequiredArgsConstructor
public class HouseController {
    private final HouseService houseService;


    @PostMapping("/register")
    public ResponseEntity<House> registerNewHouse(@RequestBody House house) {
        House newHouse = houseService.registerNewHouse(house);
        return new ResponseEntity<>(newHouse, HttpStatus.CREATED);
    }


    @PostMapping("/assign/{houseId}/donation/{donationId}")
    public ResponseEntity<House> assignHouseToDonation(@PathVariable long houseId, @PathVariable long donationId) {
        House newHouse = houseService.assignHouseToDonation(houseId, donationId);
        return new ResponseEntity<>(newHouse, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<House>> getAllHouses() {
        List<House> allHouses = houseService.getAllHouses();
        return new ResponseEntity<>(allHouses, HttpStatus.OK);
    }


    @PutMapping("/update/{houseId}")
    public ResponseEntity<House> updateHouse(@RequestBody House house, @PathVariable long houseId) {
        House updateHouse = houseService.updateHouse(house.getHouseNumber(), house.getGuardianHouse(), house.getAddress(), house.getLatitude(), house.getLongitude(), house.getLocation(), houseId);
        return new ResponseEntity<>(updateHouse, HttpStatus.OK);
    }


    @GetMapping("/{houseId}")
    public ResponseEntity<House> findHouse(@PathVariable long houseId) {
        House house = houseService.findHouseByHouseId(houseId);
        return new ResponseEntity<>(house, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{houseId}")
    public ResponseEntity<?> deleteHouse(@PathVariable long houseId) {
        houseService.deleteHouse(houseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/total-houses")
    public ResponseEntity<Integer> getTotalHouses() {
        Integer totalUsers = houseService.getTotalHouses();
        return new ResponseEntity<>(totalUsers, HttpStatus.OK);
    }

}
