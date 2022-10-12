package tech.sollabs.webflux;

import com.google.common.base.CaseFormat;
import com.infobip.spring.data.jdbc.annotation.processor.ProjectColumnCaseFormat;
import com.infobip.spring.data.jdbc.annotation.processor.ProjectTableCaseFormat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@ProjectTableCaseFormat(CaseFormat.LOWER_UNDERSCORE)
@ProjectColumnCaseFormat(CaseFormat.LOWER_UNDERSCORE)
public class WebfluxApplication {

  public static void main(String[] args) {
    SpringApplication.run(WebfluxApplication.class, args);
  }
}
