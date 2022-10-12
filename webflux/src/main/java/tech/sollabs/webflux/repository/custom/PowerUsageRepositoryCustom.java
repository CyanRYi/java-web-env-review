package tech.sollabs.webflux.repository.custom;

import reactor.core.publisher.Flux;
import tech.sollabs.webflux.resource.response.MonthlyPowerUsageResponse;

public interface PowerUsageRepositoryCustom {

  Flux<MonthlyPowerUsageResponse> retrieveMonthlyTotalData(int year);
}
