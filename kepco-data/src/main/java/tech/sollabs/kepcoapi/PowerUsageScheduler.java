package tech.sollabs.kepcoapi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tech.sollabs.kepcoapi.entity.PowerUsage;
import tech.sollabs.kepcoapi.external.KepcoApiAdapter;
import tech.sollabs.kepcoapi.repository.PowerUsageRepository;

@Slf4j
@Component
public class PowerUsageScheduler {

  private final PowerUsageRepository repository;
  private final KepcoApiAdapter kepcoApiAdapter;

  public PowerUsageScheduler(PowerUsageRepository repository,
      KepcoApiAdapter kepcoApiAdapter) {
    this.repository = repository;
    this.kepcoApiAdapter = kepcoApiAdapter;
  }

  @Scheduled(initialDelay = 1_000L, fixedDelay = 1_000_000L)
  public void initializePowerUsage() {
    LocalDate previousMonth = LocalDate.of(2013, 5, 1);

    while (previousMonth.isBefore(LocalDate.of(2022, 8, 1))) {
      try {
        saveMonthlyData(previousMonth.getYear(), previousMonth.getMonthValue());
      } catch (Throwable t) {
        log.error(String.format("Exception Occurs while retrieving Usage Data in %d-%d",
            previousMonth.getYear(), previousMonth.getMonthValue()), t);
      }
      previousMonth = previousMonth.plusMonths(1);
    }
  }

  @Transactional
  public void saveMonthlyData(int year, int month) {
    List<PowerUsage> result = new ArrayList<>();
    result.addAll(kepcoApiAdapter.retrieveHousePowerUsageData(year, month));
    result.addAll(kepcoApiAdapter.retrieveIndustryPowerUsageData(year, month));

    repository.saveAll(result);
  }

}
