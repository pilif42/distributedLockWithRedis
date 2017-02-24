package com.sample;

import com.sample.config.AppConfig;
import com.sample.distributed.DistributedLockManager;
import com.sample.distributed.impl.DistributedLockManagerRedissonImpl;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
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

  private static final String TEST_PROJECT_EXECUTION_LOCK = "testproject.request.execution";

  @Autowired
  private AppConfig appConfig;

  /**
   * This method is the entry point to the Spring Boot application.
   *
   * @param args These are the optional command line arguments
   */
  public static void main(final String[] args) throws InterruptedException {
    log.debug("About to start Application...");
    SpringApplication.run(Application.class, args);
  }

  @Bean
  public RedissonClient redissonClient() {
    Config config = new Config();
    config.useSingleServer()
            .setAddress(appConfig.getDataGrid().getAddress())
            .setPassword(appConfig.getDataGrid().getPassword());
    return Redisson.create(config);
  }

  @Bean
  public DistributedLockManager actionExportExecutionLockManager(RedissonClient redissonClient) {
    return new DistributedLockManagerRedissonImpl(TEST_PROJECT_EXECUTION_LOCK, redissonClient,
            appConfig.getDataGrid().getLockTimeToLiveSeconds());
  }
}
