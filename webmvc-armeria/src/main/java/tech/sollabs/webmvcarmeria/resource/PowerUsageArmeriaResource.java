package tech.sollabs.webmvcarmeria.resource;

import com.linecorp.armeria.server.annotation.Get;
import com.linecorp.armeria.server.annotation.Param;
import com.linecorp.armeria.server.annotation.ProducesJson;
import java.util.List;
import org.springframework.stereotype.Component;
import tech.sollabs.webmvcarmeria.configuration.ArmeriaConfiguration.ArmeriaResource;
import tech.sollabs.webmvcarmeria.resource.response.MonthlyPowerUsageResponse;
import tech.sollabs.webmvcarmeria.service.PowerUsageService;

@Component
public class PowerUsageArmeriaResource implements ArmeriaResource {

  private final PowerUsageService service;

  public PowerUsageArmeriaResource(PowerUsageService service) {
    this.service = service;
  }

  @Get("/a/monthly")
  @ProducesJson
  public List<MonthlyPowerUsageResponse> getMonthlyTotal(@Param int year) {
    return service.getMonthlyData(year);
  }
}
