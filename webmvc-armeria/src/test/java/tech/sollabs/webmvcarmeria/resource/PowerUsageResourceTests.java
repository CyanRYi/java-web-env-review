package tech.sollabs.webmvcarmeria.resource;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import tech.sollabs.webmvcarmeria.WebmvcArmeriaApiTest;
import tech.sollabs.webmvcarmeria.resource.response.MonthlyPowerUsageResponse;
import tech.sollabs.webmvcarmeria.service.PowerUsageService;

public class PowerUsageResourceTests extends WebmvcArmeriaApiTest {

  @MockBean
  private PowerUsageService service;

  @Test
  public void getMonthlyTotal_success() throws Exception {
    // Given
    MonthlyPowerUsageResponse data1 =
        new MonthlyPowerUsageResponse(2000, 1, null,
            3, 1_000L, 5_234L);

    MonthlyPowerUsageResponse data2 =
        new MonthlyPowerUsageResponse(2000, 1, "광업",
            7, 2_000L, 5_234L);

    Mockito.doReturn(List.of(data1, data2))
        .when(service)
        .getMonthlyData(eq(2000));

    // When
    ResultActions result = mockMvc.perform(
        get("/monthly")
            .queryParam("year", "2000")
            .accept(MediaType.APPLICATION_JSON));

    // Then
    result.andExpect(status().isOk())
        .andExpect(jsonPath("$.[0].year", is(2000)))
        .andExpect(jsonPath("$.[0].month", is(1)))
        .andExpect(jsonPath("$.[0].bizDivision", is(nullValue())))
        .andExpect(jsonPath("$.[0].customerCount", is(3)))
        .andExpect(jsonPath("$.[0].totalUsage", is(1_000)))
        .andExpect(jsonPath("$.[0].totalBill", is(5_234)))
        .andExpect(jsonPath("$.[1].year", is(2000)))
        .andExpect(jsonPath("$.[1].month", is(1)))
        .andExpect(jsonPath("$.[1].bizDivision", is("광업")))
        .andExpect(jsonPath("$.[1].customerCount", is(7)))
        .andExpect(jsonPath("$.[1].totalUsage", is(2_000)))
        .andExpect(jsonPath("$.[1].totalBill", is(5_234)));
  }
}
