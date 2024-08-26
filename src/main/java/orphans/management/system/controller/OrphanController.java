package orphans.management.system.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import orphans.management.system.model.House;
import orphans.management.system.model.Orphan;
import orphans.management.system.service.OrphanService;

import java.util.List;

@RestController
@RequestMapping("/orphans")
@RequiredArgsConstructor
public class OrphanController {
    private final OrphanService orphanService;


    @PostMapping("/register/house/{houseId}")
    public ResponseEntity<Orphan> registerNewOrphan(@RequestBody Orphan orphan, @PathVariable long houseId) {
        Orphan newOrphan = orphanService.registerNewOrphan(orphan, houseId);
        return new ResponseEntity<>(newOrphan, HttpStatus.CREATED);
    }


    @GetMapping("/all")
    public ResponseEntity<List<Orphan>> getAllOrphans() {
        List<Orphan> allOrphans = orphanService.getAllOrphans();
        return new ResponseEntity<>(allOrphans, HttpStatus.OK);
    }


    @PutMapping("/update/{orphanId}")
    public ResponseEntity<Orphan> updateOrphan(@RequestBody Orphan orphan, @PathVariable long orphanId) {
        Orphan updateOrphan = orphanService.updateOrphan(orphan.getFirstName(), orphan.getLastName(), orphan.getPhoneNumber(), orphan.getDateOfBirth(), orphan.getGender(), orphan.getAddress(), orphan.getOrphanType(),orphan.getGurdianHouse(), orphanId);
        return new ResponseEntity<>(updateOrphan, HttpStatus.OK);
    }


    @GetMapping("/{orphanId}")
    public ResponseEntity<Orphan> findOrphan(@PathVariable long orphanId) {
        Orphan orphan = orphanService.findByOrphanId(orphanId);
        return new ResponseEntity<>(orphan, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{orphanId}")
    public ResponseEntity<?> deleteOrphan(@PathVariable long orphanId) {
        orphanService.deleteOrphan(orphanId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/total-orphans")
    public ResponseEntity<Integer> getTotalOrphans() {
        Integer totalUsers = orphanService.getTotalOrphans();
        return new ResponseEntity<>(totalUsers, HttpStatus.OK);
    }

}
