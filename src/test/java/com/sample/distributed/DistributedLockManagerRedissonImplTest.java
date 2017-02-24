package com.sample.distributed;


import static org.mockito.Matchers.any;

import java.util.concurrent.TimeUnit;

import com.sample.distributed.impl.DistributedLockManagerRedissonImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

@RunWith(MockitoJUnitRunner.class)
public class DistributedLockManagerRedissonImplTest {

  private static final Integer TIME_TO_LIVE = 10;
  private static final String KEY = "test-lock";
  private static final String KEY_ROOT = "test-root";

  @Mock
  private RedissonClient redissonClient;

  /**
   * Before the test
   */
  @Before
  public void setup() throws Exception {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testLockOK() throws Exception {
    RLock mockLock = Mockito.mock(RLock.class);
    Mockito.when(mockLock.tryLock()).thenReturn(true);
    Mockito.when(mockLock.expire(any(Long.class), any(TimeUnit.class))).thenReturn(true);

    Mockito.when(redissonClient.getFairLock(any(String.class))).thenReturn(mockLock);

    DistributedLockManagerRedissonImpl lockManager = new DistributedLockManagerRedissonImpl(KEY_ROOT, redissonClient, TIME_TO_LIVE);
    Assert.assertTrue(lockManager.lock(KEY));
  }

  /**
   * Test
   * @throws Exception oops
   */
  @Test
  public void testLockKO() throws Exception {
    RLock mockLock = Mockito.mock(RLock.class);
    Mockito.when(mockLock.tryLock()).thenReturn(false);

    Mockito.when(redissonClient.getFairLock(any(String.class))).thenReturn(mockLock);

    DistributedLockManagerRedissonImpl impl = new DistributedLockManagerRedissonImpl(KEY_ROOT, redissonClient, TIME_TO_LIVE);
    Assert.assertFalse(impl.lock(KEY));
  }
}
