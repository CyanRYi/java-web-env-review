package tech.sollabs.webflux.resource;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import tech.sollabs.webflux.resource.response.MonthlyPowerUsageResponse;
import tech.sollabs.webflux.service.PowerUsageService;

@RestController
public class PowerUsageResource {

  private final PowerUsageService service;

  public PowerUsageResource(PowerUsageService service) {
    this.service = service;
  }

  @GetMapping(path = "/monthly", produces = MediaType.APPLICATION_JSON_VALUE)
  public Flux<MonthlyPowerUsageResponse> findAll(@RequestParam int year) {
    return service.getMonthlyData(year);
  }
}
