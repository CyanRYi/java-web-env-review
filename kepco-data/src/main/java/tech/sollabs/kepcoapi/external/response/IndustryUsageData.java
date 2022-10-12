package tech.sollabs.kepcoapi.external.response;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IndustryUsageData {

  private int year;             // 기준연도
  private int month;            // 기준월
  private String metro;         // 시/도
  private String city;          // 시군구
  private String biz;           // 사업구분
  private int custCnt;          // 전기사용 고객 호수(호)
  private long powerUsage;      // 사용량 합계(kWh)
  private long bill;            // 요금 합계(원)
  private BigDecimal unitCost;  // kWh당 단가
}
