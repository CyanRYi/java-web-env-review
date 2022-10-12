package tech.sollabs.webmvc.entity;

import java.math.BigDecimal;
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
}
