package demo.locks;

import org.junit.Test;

import java.util.concurrent.*;

public class CyclicBarrierTest {

  /**
   * 统一完成某一阶段再进入另一个阶段
   */
  @Test
  public void await() {
    CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
      System.out.println(Thread.currentThread() + "task merge result");
    });

    ExecutorService executorService = Executors.newFixedThreadPool(2);
    executorService.submit(() -> {
      try {
        System.out.println(Thread.currentThread() + "task-1 step-1");
        cyclicBarrier.await();

        System.out.println(Thread.currentThread() + "task-1 step-2");
        cyclicBarrier.await();

        System.out.println(Thread.currentThread() + "task-1 step-3");
        cyclicBarrier.await();
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
    executorService.submit(() -> {
      try {
        System.out.println(Thread.currentThread() + "task-2 step-1");
        cyclicBarrier.await();

        System.out.println(Thread.currentThread() + "task-2 step-2");
        cyclicBarrier.await();

        System.out.println(Thread.currentThread() + "task-2 step-3");
        cyclicBarrier.await();
      } catch (Exception e) {
        e.printStackTrace();
      }
    });

    executorService.shutdown();
  }

}
