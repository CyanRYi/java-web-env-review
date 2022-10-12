package tech.sollabs.webflux.service;

import com.querydsl.core.types.Projections;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import tech.sollabs.webflux.entity.QPowerUsage;
import tech.sollabs.webflux.repository.PowerUsageRepository;
import tech.sollabs.webflux.resource.response.MonthlyPowerUsageResponse;

@Service
@Transactional
public class PowerUsageService {

  private final PowerUsageRepository repository;

  public PowerUsageService(PowerUsageRepository repository) {
    this.repository = repository;
  }

  public Flux<MonthlyPowerUsageResponse> getMonthlyData(int year) {
    QPowerUsage entity = QPowerUsage.powerUsage;
    return repository.query(query -> query.select(repository.entityProjection())
            .from(entity)
            .where(entity.year.eq(year))
            .groupBy(entity.month, entity.bizDivision)
            .select(Projections.constructor(MonthlyPowerUsageResponse.class,
                entity.year, entity.month, entity.bizDivision.as("bizDivision"),
                entity.customerCount.sum().as("customerCount"),
                entity.totalUsage.sum().as("totalUsage"),
                entity.totalBill.sum().as("totalBill"))))
        .all();
  }
}
