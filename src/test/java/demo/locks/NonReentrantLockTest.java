package demo.locks;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class NonReentrantLockTest {

  @Test
  public void lock() {

  }

}

final class NonReentrantLock extends AbstractQueuedSynchronizer implements Lock {

  private static final long serialVersionUID = -833780837233068610L;

  public void lock() {
    acquire(1);
  }

  public void lockInterruptibly() throws InterruptedException {
    acquireInterruptibly(1);
  }

  public boolean tryLock() {
    return tryAcquire(1);
  }

  public boolean tryLock(long time, TimeUnit unit)
      throws InterruptedException {
    return tryAcquireNanos(1, unit.toNanos(time));
  }

  public void unlock() {
    release(1);
  }

  public boolean isHeldByCurrentThread() {
    return isHeldExclusively();
  }

  public Condition newCondition() {
    return new ConditionObject();
  }

  @Override
  protected final boolean tryAcquire(int acquires) {
    if (compareAndSetState(0, 1)) {
      setExclusiveOwnerThread(Thread.currentThread());
      return true;
    }
    return false;
  }

  @Override
  protected final boolean tryRelease(int releases) {
    if (Thread.currentThread() != getExclusiveOwnerThread()) {
      throw new IllegalMonitorStateException();
    }
    setExclusiveOwnerThread(null);
    setState(0);
    return true;
  }

  @Override
  protected final boolean isHeldExclusively() {
    return getState() != 0 && getExclusiveOwnerThread() == Thread.currentThread();
  }

}