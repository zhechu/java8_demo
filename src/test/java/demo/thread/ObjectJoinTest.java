package demo.thread;

import org.junit.Test;

public class ObjectJoinTest {

  @Test
  public void joinEndByInterrupt() throws Exception {
    // 创建线程
    Thread threadA = new Thread(() -> {
      System.out.println("threadA begin");

      for (;;) {}
    });

    // 获取主线程
    Thread mainThread = Thread.currentThread();

    // 创建线程
    Thread threadB = new Thread(() -> {
      // 延迟 3 秒执行
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println("threadB begin");

      mainThread.interrupt();
    });

    threadA.start();
    threadB.start();

    try {
      threadA.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
