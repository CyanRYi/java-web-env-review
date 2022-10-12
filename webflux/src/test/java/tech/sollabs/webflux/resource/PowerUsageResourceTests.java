package tech.sollabs.webflux.resource;

import static org.mockito.ArgumentMatchers.eq;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;
import reactor.core.publisher.Flux;
import tech.sollabs.webflux.WebfluxApiTest;
import tech.sollabs.webflux.resource.response.MonthlyPowerUsageResponse;
import tech.sollabs.webflux.service.PowerUsageService;

public class PowerUsageResourceTests extends WebfluxApiTest {

  @MockBean
  private PowerUsageService service;

  @Test
  public void getMonthlyTotal_success() {
    // Given
    MonthlyPowerUsageResponse data1 =
        new MonthlyPowerUsageResponse(2000, 1, null,
            3, 1_000L, 5_234L);

    MonthlyPowerUsageResponse data2 =
        new MonthlyPowerUsageResponse(2000, 1, "광업",
            7, 2_000L, 5_234L);

    Mockito.doReturn(Flux.just(data1, data2))
        .when(service)
        .getMonthlyData(eq(2000));

    // When
    ResponseSpec result = testClient.get()
        .uri("/monthly?year=2000")
        .accept(MediaType.APPLICATION_JSON)
        .exchange();

    // Then
    result.expectStatus().isOk()
        .expectBody()
        .jsonPath("$.[0].year").isEqualTo(2000)
        .jsonPath("$.[0].month").isEqualTo(1)
        .jsonPath("$.[0].bizDivision").isEmpty()
        .jsonPath("$.[0].customerCount").isEqualTo(3)
        .jsonPath("$.[0].totalUsage").isEqualTo(1_000)
        .jsonPath("$.[0].totalBill").isEqualTo(5_234)
        .jsonPath("$.[1].year").isEqualTo(2000)
        .jsonPath("$.[1].month").isEqualTo(1)
        .jsonPath("$.[1].bizDivision").isEqualTo("광업")
        .jsonPath("$.[1].customerCount").isEqualTo(7)
        .jsonPath("$.[1].totalUsage").isEqualTo(2_000)
        .jsonPath("$.[1].totalBill").isEqualTo(5_234);
  }
}
