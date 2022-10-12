package tech.sollabs.kepcoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KepcoApiApplication {

  public static void main(String[] args) {
    SpringApplication.run(KepcoApiApplication.class, args);
  }

}
