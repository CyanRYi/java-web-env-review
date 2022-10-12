package tech.sollabs.kepcoapi.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.sollabs.kepcoapi.entity.PowerUsage;

@Repository
public interface PowerUsageRepository extends JpaRepository<PowerUsage, UUID> {
}
