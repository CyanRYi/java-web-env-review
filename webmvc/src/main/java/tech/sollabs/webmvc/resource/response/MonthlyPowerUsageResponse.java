package tech.sollabs.webmvc.resource.response;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class MonthlyPowerUsageResponse {

  private final int year;
  private final int month;
  private final String bizDivision;
  private final int customerCount;
  private final long totalUsage;
  private final long totalBill;

  @QueryProjection
  public MonthlyPowerUsageResponse(int year, int month, String bizDivision, int customerCount,
      long totalUsage, long totalBill) {
    this.year = year;
    this.month = month;
    this.bizDivision = bizDivision;
    this.customerCount = customerCount;
    this.totalUsage = totalUsage;
    this.totalBill = totalBill;
  }
}
