package tech.sollabs.webflux.entity;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("power_usage")
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class PowerUsage {

  public enum UsageType {
    HOUSE,
    INDUSTRY
  }

  @Id
  private UUID id;
  @Column("`year`")
  private int year;
  @Column("`month`")
  private int month;
  private UsageType type;
  private String metro;
  private String city;
  @Column("`biz_division`")
  private String bizDivision;
  @Column("`customer_count`")
  private int customerCount;
  @Column("`total_usage`")
  private long totalUsage;
  @Column("`total_bill`")
  private long totalBill;
  @Column("`unit_cost`")
  private BigDecimal unitCost;
}
