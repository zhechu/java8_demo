package demo.locks;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {

  @Test
  public void release() {
    Semaphore semaphore = new Semaphore(0);

    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.submit(() -> {
      System.out.println(Thread.currentThread() + "over");
      semaphore.release();
    });
    executorService.submit(() -> {
      System.out.println(Thread.currentThread() + "over");
      semaphore.release();
    });

    try {
      semaphore.acquire(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(Thread.currentThread() + "over");

    executorService.shutdown();
  }

  @Test
  public void fair() {
    Semaphore semaphore = new Semaphore(0, true);

    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.submit(() -> {
      System.out.println(Thread.currentThread() + "over");
      semaphore.release();
    });
    executorService.submit(() -> {
      System.out.println(Thread.currentThread() + "over");
      semaphore.release();
    });
    executorService.submit(() -> {
      System.out.println(Thread.currentThread() + "start");
      try {
        semaphore.acquire(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread() + "end");

      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      semaphore.release();
    });

    try {
      Thread.sleep(1000);
      semaphore.acquire(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(Thread.currentThread() + "over");

    executorService.shutdown();
  }

  /**
   * 模拟 {@link java.util.concurrent.CyclicBarrier}
   */
  @Test
  public void step() {
    Semaphore semaphore = new Semaphore(0);

    ExecutorService executorService = Executors.newFixedThreadPool(2);

    executorService.submit(() -> {
      System.out.println(Thread.currentThread() + "task-1 step-1");
      semaphore.release();
    });
    executorService.submit(() -> {
      System.out.println(Thread.currentThread() + "task-2 step-1");
      semaphore.release();
    });

    try {
      semaphore.acquire(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(Thread.currentThread() + "over-1");

    executorService.submit(() -> {
      System.out.println(Thread.currentThread() + "task-3 step-2");
      semaphore.release();
    });
    executorService.submit(() -> {
      System.out.println(Thread.currentThread() + "task-4 step-2");
      semaphore.release();
    });

    try {
      semaphore.acquire(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(Thread.currentThread() + "over-2");

    executorService.submit(() -> {
      System.out.println(Thread.currentThread() + "task-5 step-3");
      semaphore.release();
    });
    executorService.submit(() -> {
      System.out.println(Thread.currentThread() + "task-6 step-3");
      semaphore.release();
    });

    try {
      semaphore.acquire(2);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(Thread.currentThread() + "over-3");
  }

}
