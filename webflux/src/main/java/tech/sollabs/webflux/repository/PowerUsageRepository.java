package tech.sollabs.webflux.repository;

import com.infobip.spring.data.r2dbc.QuerydslR2dbcRepository;
import java.util.UUID;
import tech.sollabs.webflux.entity.PowerUsage;
import tech.sollabs.webflux.repository.custom.PowerUsageRepositoryCustom;

/**
 * @see <a href="https://github.com/infobip/infobip-spring-data-querydsl#R2DBCSetup" />
 */
public interface PowerUsageRepository
    extends QuerydslR2dbcRepository<PowerUsage, UUID>, PowerUsageRepositoryCustom {
}
