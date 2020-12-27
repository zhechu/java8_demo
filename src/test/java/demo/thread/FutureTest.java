package demo.thread;

import org.junit.Test;

import java.util.concurrent.*;

public class FutureTest {

  /**
   * 复现 {@link Future#get()} 的BUG
   */
  @Test
  public void getBug() throws Exception {
    // 线程池只有1个线程，队列只有1个元素
    ThreadPoolExecutor executor = new ThreadPoolExecutor(
        1,
        1,
        1L,
        TimeUnit.MINUTES,
        new ArrayBlockingQueue<>(1),
//        new ThreadPoolExecutor.DiscardPolicy() // futureThree 复现被无限阻塞 BUG
//        new ThreadPoolExecutor.DiscardOldestPolicy() // 复现随机任务被无限阻塞 BUG
//        new ThreadPoolExecutor.AbortPolicy()
        new CustomDiscardPolicy()
    );

    // 添加任务 one
    Future futureOne = executor.submit(() -> {
      System.out.println("start runnable one");

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    // 添加任务 two
    Future futureTwo = executor.submit(() -> {
      System.out.println("start runnable two");
    });

    Future futureThree = null;
    try {
      futureThree = executor.submit(() -> {
        System.out.println("start runnable three");
      });
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println("task one " + futureOne.get());
    System.out.println("task two " + futureTwo.get());
    System.out.println("task three " + (futureThree == null ? null : futureThree.get()));

    executor.shutdown();
  }

}

/**
 * 自定义拒绝策略，修复 Future 被无限阻塞 BUG
 */
class CustomDiscardPolicy implements java.util.concurrent.RejectedExecutionHandler {

  @Override
  public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
    if (!executor.isShutdown()) {
      if (r instanceof FutureTask) {
        ((FutureTask) r).cancel(true);
      }
    }
  }

}
