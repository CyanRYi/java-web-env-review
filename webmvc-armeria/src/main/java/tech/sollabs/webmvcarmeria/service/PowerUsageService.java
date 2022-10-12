package tech.sollabs.webmvcarmeria.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.sollabs.webmvcarmeria.repository.PowerUsageRepository;
import tech.sollabs.webmvcarmeria.resource.response.MonthlyPowerUsageResponse;

@Service
@Transactional
public class PowerUsageService {

  private final PowerUsageRepository repository;

  public PowerUsageService(PowerUsageRepository repository) {
    this.repository = repository;
  }

  public List<MonthlyPowerUsageResponse> getMonthlyData(int year) {
    return repository.retrieveMonthlyTotalData(year);
  }
}
