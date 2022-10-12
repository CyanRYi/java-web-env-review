package tech.sollabs.webmvcarmeria.resource;

import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.sollabs.webmvcarmeria.resource.response.MonthlyPowerUsageResponse;
import tech.sollabs.webmvcarmeria.service.PowerUsageService;

@RestController
public class PowerUsageResource {

  private final PowerUsageService service;

  public PowerUsageResource(PowerUsageService service) {
    this.service = service;
  }

  @GetMapping(path = "/monthly", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<MonthlyPowerUsageResponse> getMonthlyTotal(@RequestParam int year) {
    return service.getMonthlyData(year);
  }
}
