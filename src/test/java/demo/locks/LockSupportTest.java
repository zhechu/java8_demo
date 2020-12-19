package demo.locks;

import org.junit.Test;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {

  @Test
  public void park() {
    System.out.println("begin ...");

    LockSupport.park();

    System.out.println("end ...");
  }

  @Test
  public void parkForInterrupt() throws InterruptedException {
    System.out.println("main begin ...");

    Thread threadA = new Thread(() -> {
      System.out.println("threadA begin ...");

      // 被打断唤醒后不会抛异常
      LockSupport.park();

      System.out.println("threadA end ...");
    });

    Thread threadB = new Thread(() -> {
      System.out.println("threadB begin ...");

      System.out.println("threadB sleep 3s ...");
      LockSupport.parkNanos(3000000000L);

      threadA.interrupt();
      System.out.println("threadB interrupt threadA ...");

      System.out.println("threadB end ...");
    });

    threadA.start();
    threadB.start();

    threadA.join();
    threadB.join();

    System.out.println("main end ...");
  }

  @Test
  public void unpark() throws InterruptedException {
    System.out.println("main begin ...");

    Thread threadA = new Thread(() -> {
      System.out.println("threadA begin ...");

      // 等待调用 unpark 唤醒
      LockSupport.park();

      System.out.println("threadA end ...");
    });

    Thread threadB = new Thread(() -> {
      System.out.println("threadB begin ...");

      System.out.println("threadB sleep 3s ...");
      LockSupport.parkNanos(3000000000L);

      LockSupport.unpark(threadA);
      System.out.println("threadB unpark threadA ...");

      System.out.println("threadB end ...");
    });

    threadA.start();
    threadB.start();

    threadA.join();
    threadB.join();

    System.out.println("main end ...");
  }

  /**
   * 先调 unpark 后调 park，则直接返回
   */
  @Test
  public void unparkAfterPark() {
    System.out.println("begin ...");

    System.out.println("unpark ...");
    LockSupport.unpark(Thread.currentThread());

    System.out.println("park ...");
    LockSupport.park();

    System.out.println("end ...");
  }

  @Test
  public void quitByInterrupt() throws InterruptedException {
    System.out.println("main begin ...");

    Thread threadA = new Thread(() -> {
      System.out.println("threadA begin ...");

      // 只有被中断才会退出循环
      while (!Thread.currentThread().isInterrupted()) {
        System.out.println("threadA park start ...");

        LockSupport.park();

        System.out.println("threadA park end ...");
      }


      System.out.println("threadA end ...");
    });

    Thread threadB = new Thread(() -> {
      System.out.println("threadB begin ...");

      System.out.println("threadB sleep 3s ...");
      LockSupport.parkNanos(3000000000L);

      LockSupport.unpark(threadA);
      System.out.println("threadB unpark threadA ...");

//      threadA.interrupt();
//      System.out.println("threadB interrupt threadA ...");

      System.out.println("threadB end ...");
    });

    threadA.start();
    threadB.start();

    threadA.join();
    threadB.join();

    System.out.println("main end ...");
  }

  /**
   * 先进先出锁实现
   */
  @Test
  public void fifoMutex() {
    FIFOMutex fifoMutex = new FIFOMutex();

  }

  /**
   * 先进先出锁
   */
  class FIFOMutex {

    private final AtomicBoolean locked = new AtomicBoolean(false);
    private final Queue<Thread> waiters = new ConcurrentLinkedQueue<>();

    public void lock() {
      boolean wasInterrupted = false;
      Thread current = Thread.currentThread();
      waiters.add(current);

      // 只有队首的线程才可以获取锁
      while (waiters.peek() != current || locked.compareAndSet(false, true)) {
        LockSupport.park(this);

        // 若被打断，则清除打断标记
        if (Thread.interrupted()) {
          wasInterrupted = true;
        }
      }

      waiters.remove();

      // 若被打断，则还原打断标记
      if (wasInterrupted) {
        current.interrupt();
      }
    }

    public void unlock() {
      locked.set(false);
      LockSupport.unpark(waiters.peek());
    }

  }

}
