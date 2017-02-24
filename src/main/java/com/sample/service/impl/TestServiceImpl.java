package com.sample.service.impl;

import com.sample.distributed.DistributedLockManager;
import com.sample.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestServiceImpl implements TestService {

  private static final String TEST_LOCK = "testLock";

  @Autowired
  private DistributedLockManager lockManager;

  @Override
  public void completeSomeProcess() {
    log.debug("completeSomeProcess ...");
    if (!lockManager.isLocked(TEST_LOCK) && lockManager.lock(TEST_LOCK)) {
      try {
        log.debug("lock acquired - about to process...");
        Thread.sleep(10000);  // replace with some real processing, ie process files from a directory or ...
      } catch (InterruptedException e) {
        // TODO
      } finally {
        log.debug("processing done - about to release lock");
        lockManager.unlock(TEST_LOCK);
        log.debug("lock released");
      }
    }
  }
}
