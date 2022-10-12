package tech.sollabs.webflux;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest
public class WebfluxApiTest {

  @Autowired
  private ApplicationContext context;

  protected WebTestClient testClient;

  @BeforeEach
  public void setup() {
    this.testClient = WebTestClient
        .bindToApplicationContext(this.context)
        .configureClient()
//        .apply(springSecurity())
//        .filter(basicAuthentication())
        .build();
  }
}
