package tech.sollabs.webmvcarmeria.repository.custom;

import java.util.List;
import tech.sollabs.webmvcarmeria.resource.response.MonthlyPowerUsageResponse;

public interface PowerUsageRepositoryCustom {

  List<MonthlyPowerUsageResponse> retrieveMonthlyTotalData(int year);
}
