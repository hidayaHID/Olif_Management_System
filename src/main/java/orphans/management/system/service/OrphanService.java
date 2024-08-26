package orphans.management.system.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import orphans.management.system.model.House;
import orphans.management.system.model.Orphan;
import orphans.management.system.repository.OrphanRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrphanService {
    private final OrphanRepository orphanRepository;
    private final HouseService houseService;


    public Orphan registerNewOrphan(Orphan orphan, long houseId) {
        House house = houseService.findHouseByHouseId(houseId);
        orphan.setHouse(house);
        orphan.setAddress(house.getAddress());
        log.info("New orphan registered.");
        return orphanRepository.save(orphan);
    }


    public List<Orphan> getAllOrphans() {
        log.info("Orphans retrieved successfully.");
        return orphanRepository.findAll();
    }


    public Orphan updateOrphan(String firstName, String lastName, String phoneNumber, String dateOfBirth, String gender, String address, String orphanType, String gurdianHouse, long orphanId) {
        Orphan updateHouse = findByOrphanId(orphanId);
        updateHouse.setFirstName(firstName);
        updateHouse.setLastName(lastName);
        updateHouse.setPhoneNumber(phoneNumber);
        updateHouse.setDateOfBirth(dateOfBirth);
        updateHouse.setGender(gender);
        updateHouse.setAddress(address);
        updateHouse.setOrphanType(orphanType);
        log.info("Orphan updated successfully.");
        return orphanRepository.save(updateHouse);
    }


    public Orphan findByOrphanId(long orphanId) {
        Orphan orphan = orphanRepository.findByOrphanId(orphanId);
        if (orphan == null) {
            log.error("Orphan not found");
            throw new RuntimeException("Orphan not found");
        }

        log.info("Orphan retrieved.");
        return orphan;
    }


    public void deleteOrphan(long orphanId) {
        Orphan orphan = findByOrphanId(orphanId);
        if (orphan == null) {
            log.error("Orphan not found");
            throw new RuntimeException("Orphan not found");
        }

        log.info("Orphan deleted.");
        orphanRepository.deleteByOrphanId(orphanId);
    }

    public Integer getTotalOrphans() {
        return orphanRepository.getTotalOrphans();
    }

}
