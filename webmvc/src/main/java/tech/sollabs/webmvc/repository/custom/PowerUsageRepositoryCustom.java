package tech.sollabs.webmvc.repository.custom;

import java.util.List;
import tech.sollabs.webmvc.resource.response.MonthlyPowerUsageResponse;

public interface PowerUsageRepositoryCustom {

  List<MonthlyPowerUsageResponse> retrieveMonthlyTotalData(int year);
}
