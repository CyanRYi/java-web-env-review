package tech.sollabs.kepcoapi.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import tech.sollabs.kepcoapi.entity.PowerUsage;
import tech.sollabs.kepcoapi.external.response.HouseUsageData;
import tech.sollabs.kepcoapi.external.response.IndustryUsageData;
import tech.sollabs.kepcoapi.type.KepcoProvinceCode;

@Component
@Slf4j
public class KepcoApiAdapter {

  @Value("${kepco.api-key}")
  private String apiKey;
  private final ObjectMapper objectMapper;
  private final RestTemplate restTemplate;

  public KepcoApiAdapter(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
    this.restTemplate = new RestTemplate();
    restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
  }

  /**
   * 산업별 전기 사용량 Data
   * 2007년 1월 데이터부터 API로 제공
   * @see <a href="https://bigdata.kepco.co.kr/cmsmain.do?scode=S01&pcode=000493&pstate=indus" />
   */
  public List<PowerUsage> retrieveIndustryPowerUsageData(int year, int month) {
    List<IndustryUsageData> result = new ArrayList<>();

    for (KepcoProvinceCode province : KepcoProvinceCode.values()) {
      URI uri = UriComponentsBuilder.fromUriString(
              "https://bigdata.kepco.co.kr/openapi/v1/powerUsage/industryType.do")
          .queryParam("apiKey", apiKey)
          .queryParam("year", String.valueOf(year))
          .queryParam("month", String.format("%02d", month))
          .queryParam("metroCd", province.getCode())
          .encode(StandardCharsets.UTF_8)
          .build()
          .toUri();

      String response = restTemplate
          .exchange(RequestEntity.get(uri).build(), String.class)
          .getBody();

      Map<String, List<IndustryUsageData>> data;
      try {
        data = objectMapper.readValue(response, new TypeReference<>() {});
        result.addAll(data.get("data"));
      } catch (JsonProcessingException e) {
        log.error("Failed while converting JSON data", e);
      }
    }

    return result.stream()
        .map(PowerUsage::from)
        .collect(Collectors.toList());
  }

  /**
   * 가구별 전기 사용량 Data
   * 2013년 5월 데이터부터 API로 제공
   * @see <a href="https://bigdata.kepco.co.kr/cmsmain.do?scode=S01&pcode=000493&pstate=house" />
   */
  public List<PowerUsage> retrieveHousePowerUsageData(int year, int month) {
    List<HouseUsageData> result = new ArrayList<>();

    for (KepcoProvinceCode province : KepcoProvinceCode.values()) {
      URI uri = UriComponentsBuilder.fromUriString(
              "https://bigdata.kepco.co.kr/openapi/v1/powerUsage/houseAve.do")
          .queryParam("apiKey", apiKey)
          .queryParam("year", String.valueOf(year))
          .queryParam("month", String.format("%02d", month))
          .queryParam("metroCd", province.getCode())
          .encode(StandardCharsets.UTF_8)
          .build()
          .toUri();

      String response = restTemplate
          .exchange(RequestEntity.get(uri).build(), String.class)
          .getBody();

      Map<String, List<HouseUsageData>> data;
      try {
        data = objectMapper.readValue(response, new TypeReference<>() {});
        result.addAll(data.get("data"));

      } catch (JsonProcessingException e) {
        log.error("Failed while converting JSON data", e);
      }
    }

    return result.stream()
        .map(PowerUsage::from)
        .collect(Collectors.toList());
  }
}
