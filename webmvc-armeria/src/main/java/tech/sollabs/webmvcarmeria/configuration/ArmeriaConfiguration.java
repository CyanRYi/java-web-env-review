package tech.sollabs.webmvcarmeria.configuration;

import com.linecorp.armeria.server.docs.DocService;
import com.linecorp.armeria.server.tomcat.TomcatService;
import com.linecorp.armeria.spring.ArmeriaServerConfigurator;
import java.util.Collection;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @see <a href="https://www.slideshare.net/GihwanKim10/hello-armeria-bye-spring" />
 */
@Configuration
public class ArmeriaConfiguration {

  @Bean
  public ArmeriaServerConfigurator configureServer(
      ServletWebServerApplicationContext ctx, Collection<ArmeriaResource> armeriaResources) {
    TomcatWebServer container = (TomcatWebServer) ctx.getWebServer();
    container.start();

    return sb -> {
      sb.serviceUnder("/", TomcatService.of(container.getTomcat()))
          .serviceUnder("/doc", new DocService());

      armeriaResources.forEach(sb::annotatedService);
    };
  }

  public interface ArmeriaResource {

  }
}
