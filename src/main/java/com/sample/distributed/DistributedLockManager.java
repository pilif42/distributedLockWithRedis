package com.sample.distributed;

public interface DistributedLockManager {

  /**
   * for the given named lock, lock it.
   *
   * @param key the name of the lock
   * @return true if locked
   */
  boolean lock(String key);

  /**
   * unlock the given named lock, if indeed the lock manager originally locked
   * it, and the calling thread is the thread that created the lock in the first
   * place..
   *
   * @param key the lock name
   */
  void unlock(String key);

  /**
   * find if the named lock is locked
   * @param key the name of the lock
   * @return true if the lock exists and was created by this manager. The lock may not have been created by the calling thread.
   */
  boolean isLocked(String key);

  /**
   * force unlock of the distributed locks created by this DistributedLockManager instance
   *
   */
  void unlockInstanceLocks();

}