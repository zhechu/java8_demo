package demo.thread;

import org.junit.Test;

public class ObjectYieldTest {

  @Test
  public void yield() {
    Runnable runnable = () -> {
      for (int i = 0; i < 5; i++) {
        if (i % 5 == 0) {
          System.out.println(Thread.currentThread() + " yield cpu ...");

          // 当前线程让出 CPU 执行权，放弃时间片，进行下一轮调度
          Thread.yield();
        }

        System.out.println(Thread.currentThread() + " is over");
      }
    };

    new Thread(runnable).start();
    new Thread(runnable).start();
    new Thread(runnable).start();
  }

}
