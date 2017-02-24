package com.sample.service.impl;

import com.sample.distributed.DistributedLockManager;
import com.sample.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TestServiceImpl implements TestService {

  @Autowired
  private DistributedLockManager lockManager;

  @Override
  public void completeSomeProcess() {
    log.debug("completeSomeProcess ...");

    // For instance, browse a directory for files and pick one file for processing
    String fileNameToBeProcessed = "theFileDealtWithByThisInstance";

    if (!lockManager.isLocked(fileNameToBeProcessed) && lockManager.lock(fileNameToBeProcessed)) {
      try {
        log.debug("lock acquired - about to process...");
        Thread.sleep(10000);  // replace with some real processing, ie parse the file 
      } catch (InterruptedException e) {
        // TODO
      } finally {
        log.debug("processing done - about to release lock");
        lockManager.unlock(fileNameToBeProcessed);
        log.debug("lock released");
      }
    }
  }
}
