package com.sample.endpoint;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestEndpoint {
  @RequestMapping("/totest")
  @ResponseStatus(HttpStatus.OK)
  public void launchTest() {
    log.debug("launchTest....");
  }
}
