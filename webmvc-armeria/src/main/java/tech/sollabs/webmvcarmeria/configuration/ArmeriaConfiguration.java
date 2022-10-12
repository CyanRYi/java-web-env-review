package tech.sollabs.webmvcarmeria.configuration;

import com.linecorp.armeria.client.WebClient;
import com.linecorp.armeria.client.logging.ContentPreviewingClient;
import com.linecorp.armeria.client.logging.LoggingClient;
import com.linecorp.armeria.client.retry.RetryRule;
import com.linecorp.armeria.client.retry.RetryingClient;
import com.linecorp.armeria.server.tomcat.TomcatService;
import com.linecorp.armeria.spring.ArmeriaServerConfigurator;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @see <a href="https://www.slideshare.net/GihwanKim10/hello-armeria-bye-spring" />
 */
@Configuration
public class ArmeriaConfiguration {

  /**
   * armeria client만을 사용한다면 이쪽 설정으로.
   * {@code armeriaWebClient.get("...").aggregate().join } 과 같이 쓴다고 한다.
   */
  @Bean
  public WebClient armeriaWebClient() {
    return WebClient.builder()
        .decorator(LoggingClient.newDecorator())
        .decorator(ContentPreviewingClient.newDecorator(2048))
        .decorator(RetryingClient.newDecorator(RetryRule.of(RetryRule.failsafe())))
        //.decorator(CircuitBreakerClient.newDecorator())
        .build();
  }

  @Bean
  public ArmeriaServerConfigurator configureServer(ServletWebServerApplicationContext ctx) {
    TomcatWebServer container = (TomcatWebServer) ctx.getWebServer();
    container.start();

    return sb -> {
      sb.serviceUnder("/", TomcatService.of(container.getTomcat()));
    };
  }
}
