package tech.sollabs.webmvc.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;
import tech.sollabs.webmvc.entity.PowerUsage;
import tech.sollabs.webmvc.entity.QPowerUsage;
import tech.sollabs.webmvc.repository.custom.PowerUsageRepositoryCustom;

@Repository
public interface PowerUsageRepository extends
    JpaRepository<PowerUsage, UUID>, PowerUsageRepositoryCustom,
    QuerydslPredicateExecutor<PowerUsage>, QuerydslBinderCustomizer<QPowerUsage> {

  @Override
  default void customize(QuerydslBindings bindings, QPowerUsage root) {
    bindings.including(root.type);
    bindings.bind(root.type)
        .firstOptional((path, value) -> value.map(path::eq));
  }
}
