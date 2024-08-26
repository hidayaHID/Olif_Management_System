package orphans.management.system.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import orphans.management.system.model.House;
import orphans.management.system.model.Orphan;

@Repository
public interface OrphanRepository extends JpaRepository<Orphan, Long> {
    Orphan findByOrphanId(long orphanId);

    void deleteByOrphanId(long orphanId);

    @Query("SELECT COUNT(*) FROM Orphan o")
    Integer getTotalOrphans();
}
