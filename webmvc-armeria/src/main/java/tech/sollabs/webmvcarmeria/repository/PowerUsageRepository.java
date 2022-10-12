package tech.sollabs.webmvcarmeria.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;
import tech.sollabs.webmvcarmeria.entity.PowerUsage;
import tech.sollabs.webmvcarmeria.entity.QPowerUsage;
import tech.sollabs.webmvcarmeria.repository.custom.PowerUsageRepositoryCustom;

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
