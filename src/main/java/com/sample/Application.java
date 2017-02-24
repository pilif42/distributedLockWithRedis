package com.sample;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * The 'main' entry point
 */
@SpringBootApplication
@EnableAsync
@EnableCaching
@EnableScheduling
@Slf4j
public class Application {
  /**
   * This method is the entry point to the Spring Boot application.
   *
   * @param args These are the optional command line arguments
   */
  public static void main(final String[] args) throws InterruptedException {
    log.debug("About to start Application...");
    SpringApplication.run(Application.class, args);
  }
}
