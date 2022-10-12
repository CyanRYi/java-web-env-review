package tech.sollabs.webmvcarmeria.repository.custom;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import tech.sollabs.webmvcarmeria.entity.QPowerUsage;
import tech.sollabs.webmvcarmeria.resource.response.MonthlyPowerUsageResponse;

public class PowerUsageRepositoryCustomImpl implements PowerUsageRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  public PowerUsageRepositoryCustomImpl(JPAQueryFactory queryFactory) {
    this.queryFactory = queryFactory;
  }

  public List<MonthlyPowerUsageResponse> retrieveMonthlyTotalData(int year) {
    QPowerUsage data = QPowerUsage.powerUsage;
    return queryFactory.from(data)
        .where(data.year.eq(year))
        .groupBy(data.month, data.bizDivision)
        .select(Projections.constructor(MonthlyPowerUsageResponse.class,
            data.year, data.month, data.bizDivision,
            data.customerCount.sum(), data.totalUsage.sum(), data.totalBill.sum()))
        .fetch();
  }
}
