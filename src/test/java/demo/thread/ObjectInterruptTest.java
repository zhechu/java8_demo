package demo.thread;

import org.junit.Test;

public class ObjectInterruptTest {

  @Test
  public void interrupt() throws Exception {
    // 创建线程
    Thread threadA = new Thread(() -> {
      while (!Thread.currentThread().isInterrupted()) {
        System.out.println(Thread.currentThread() + " run ...");
      }
    });

    threadA.start();

    // 主线程休眠 2 毫秒
    Thread.sleep(2);

    // 中断子线程
    System.out.println("main thread interrupt threadA");
    threadA.interrupt();

    // 等待子线程执行完毕
    threadA.join();

    System.out.println("main over");
  }

  @Test
  public void interrupted() throws Exception {
    // 创建线程
    Thread threadA = new Thread(() -> {
      // 中断标记为 true 则会退出循环，并且清除中断标记
      while (!Thread.currentThread().interrupted()) {
        System.out.println(Thread.currentThread() + " run ...");
      }

      System.out.println("threadA isInterrupted:" + Thread.currentThread().isInterrupted());
    });

    threadA.start();

    // 主线程休眠 2 毫秒
    Thread.sleep(2);

    // 设置中断标记
    threadA.interrupt();

    // 等待子线程执行完毕
    threadA.join();

    System.out.println("main over");
  }

}
