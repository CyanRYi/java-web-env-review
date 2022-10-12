package tech.sollabs.webflux.configuration;

import com.querydsl.sql.H2Templates;
import com.querydsl.sql.SQLTemplates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class R2dbcConfiguration {

  @Bean
  public SQLTemplates sqlTemplates() {
    return new H2Templates();
  }
}
