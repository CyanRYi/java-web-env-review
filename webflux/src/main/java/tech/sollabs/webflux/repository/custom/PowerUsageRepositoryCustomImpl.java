package tech.sollabs.webflux.repository.custom;

import com.querydsl.core.types.Projections;
import com.querydsl.sql.SQLQuery;
import com.querydsl.sql.SQLQueryFactory;
import org.springframework.data.r2dbc.convert.EntityRowMapper;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import reactor.core.publisher.Flux;
import tech.sollabs.webflux.entity.QPowerUsage;
import tech.sollabs.webflux.resource.response.MonthlyPowerUsageResponse;

public class PowerUsageRepositoryCustomImpl implements PowerUsageRepositoryCustom {

  private final SQLQueryFactory sqlQueryFactory;
  private final DatabaseClient databaseClient;
  private final R2dbcConverter converter;

  public PowerUsageRepositoryCustomImpl(SQLQueryFactory sqlQueryFactory,
      DatabaseClient databaseClient, R2dbcConverter converter) {
    this.sqlQueryFactory = sqlQueryFactory;
    this.databaseClient = databaseClient;
    this.converter = converter;
  }

  /**
   * .as()를 통해 Alias를 지정하지 않으면 PropertyBinding이 돌아가지 않는다.
   */
  @Override
  public Flux<MonthlyPowerUsageResponse> retrieveMonthlyTotalData(int year) {
    QPowerUsage data = QPowerUsage.powerUsage;
    SQLQuery<MonthlyPowerUsageResponse> query = sqlQueryFactory.query()
        .from(data)
        .where(data.year.eq(year))
        .groupBy(data.month, data.bizDivision)
        .select(Projections.constructor(MonthlyPowerUsageResponse.class,
            data.year, data.month, data.bizDivision,
            data.customerCount.sum().as("customerCount"),
            data.totalUsage.sum().as("totalUsage"),
            data.totalBill.sum().as("totalBill")));

    return query(query).all();
  }

  /**
   * @see com.infobip.spring.data.r2dbc.ReactiveQuerydslR2dbcPredicateExecutor#query(SQLQuery)
   */
  private <O> RowsFetchSpec<O> query(SQLQuery<O> query) {
    query.setUseLiterals(true);
    String sql = query.getSQL().getSQL();
    EntityRowMapper<O> mapper = new EntityRowMapper<>(query.getType(), converter);
    return databaseClient.sql(sql)
        .map(mapper);
  }
}
