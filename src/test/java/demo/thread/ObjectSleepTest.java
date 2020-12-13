package demo.thread;

import org.junit.Test;

public class ObjectSleepTest {

  @Test
  public void sleepEndByInterrupt() throws Exception {
    // 创建线程
    Thread threadA = new Thread(() -> {
      System.out.println("threadA begin");

      try {
        Thread.sleep(10000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println("threadA end");
    });

    // 创建线程
    Thread threadB = new Thread(() -> {
      // 延迟 3 秒执行
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println("threadB begin");

      threadA.interrupt();

      System.out.println("threadB end");
    });

    threadA.start();
    threadB.start();

    threadA.join();
    threadB.join();

    System.out.println("main over");
  }

}
