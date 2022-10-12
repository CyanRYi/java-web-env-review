package tech.sollabs.kepcoapi.external.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HouseUsageData {

  private int year;             // 기준연도
  private int month;            // 기준월
  private String metro;         // 시/도
  private String city;          // 시군구
  private int houseCnt;         // 전기사용 고객 호수(호)
  private int powerUsage;       // 사용량 합계(kWh)
  private int bill;             // 요금 평균(원)
}
