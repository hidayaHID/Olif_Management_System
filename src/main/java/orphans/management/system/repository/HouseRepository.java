package orphans.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import orphans.management.system.model.House;
import orphans.management.system.model.Orphan;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {

    House findByHouseId(long houseId);

    void deleteByHouseId(long houseId);

    @Query("SELECT COUNT(*) FROM House h")
    Integer getTotalHouses();
}
