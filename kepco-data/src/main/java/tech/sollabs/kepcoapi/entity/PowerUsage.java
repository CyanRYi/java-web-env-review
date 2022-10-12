package tech.sollabs.kepcoapi.entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import tech.sollabs.kepcoapi.type.UsageType;
import tech.sollabs.kepcoapi.external.response.HouseUsageData;
import tech.sollabs.kepcoapi.external.response.IndustryUsageData;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class PowerUsage {

  @Id
  @GeneratedValue
  @Type(type = "uuid-char")
  private UUID id;

  @Column(nullable = false)
  private int year;

  @Column(nullable = false)
  private int month;

  @Column(nullable = false, length = 10)
  @Enumerated(EnumType.STRING)
  private UsageType type;

  @Column(nullable = false, length = 20)
  private String metro;

  @Column(nullable = false, length = 20)
  private String city;

  @Column(length = 50)
  private String bizDivision;

  @Column(nullable = false)
  private int customerCount;

  @Column(nullable = false)
  private long totalUsage;

  @Column(nullable = false)
  private long totalBill;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal unitCost;

  protected PowerUsage() {
  }

  private PowerUsage(IndustryUsageData usage) {
    this.id = UUID.randomUUID();
    this.year = usage.getYear();
    this.month = usage.getMonth();
    this.type = UsageType.INDUSTRY;
    this.metro = usage.getMetro();
    this.city = usage.getCity();

    this.bizDivision = usage.getBiz();
    this.customerCount = usage.getCustCnt();
    this.totalUsage = usage.getPowerUsage();
    this.totalBill = usage.getBill();
    this.unitCost = usage.getUnitCost();
  }

  private PowerUsage(HouseUsageData usage) {
    this.id = UUID.randomUUID();
    this.year = usage.getYear();
    this.month = usage.getMonth();
    this.type = UsageType.HOUSE;
    this.metro = usage.getMetro();
    this.city = usage.getCity();

    this.bizDivision = null;
    this.customerCount = usage.getHouseCnt();
    this.totalUsage = usage.getPowerUsage();
    this.totalBill = (long) usage.getBill() * getCustomerCount();
    this.unitCost = BigDecimal.valueOf(this.totalBill)
        .divide(BigDecimal.valueOf(usage.getPowerUsage()), RoundingMode.HALF_UP);
  }

  public static PowerUsage from(IndustryUsageData usage) {
    return new PowerUsage(usage);
  }

  public static PowerUsage from(HouseUsageData usage) {
    return new PowerUsage(usage);
  }
}
