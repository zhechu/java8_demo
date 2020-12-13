package demo.thread;

import org.junit.Test;

public class ObjectWaitTest {

  private static final Object resourceA = new Object();
  private static final Object resourceB = new Object();

  @Test
  public void deadlock() throws Exception {
    // 创建线程
    Thread threadA = new Thread(() -> {
      try {
        // 获取 resourceA 共享资源的监视器锁
        synchronized (resourceA) {
          System.out.println("threadA get resourceA lock");
          // 获取 resourceB 共享资源的监视器锁
          synchronized (resourceB) {
            System.out.println("threadA get resourceB lock");

            // 线程 A 阻塞，并释放获取到的 resourceA 锁
            System.out.println("threadA release resourceA lock");
            resourceA.wait();
          }
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    // 创建线程
    Thread threadB = new Thread(() -> {
      try {
        // 休眠 1 秒
        Thread.sleep(1000);

        // 获取 resourceA 共享资源的监视器锁
        synchronized (resourceA) {
          System.out.println("threadB get resourceA lock");

          // 预期在这发生死锁（threadA 未释放 resourceA 锁）

          // 获取 resourceB 共享资源的监视器锁
          synchronized (resourceB) {
            System.out.println("threadB get resourceB lock");

            // 线程 A 阻塞，并释放获取到的 resourceA 锁
            System.out.println("threadB release resourceA lock");
            resourceA.wait();
          }
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    // 启动线程
    threadA.start();
    threadB.start();

    // 等待两个线程结束
    threadA.join();
    threadB.join();

    System.out.println("main over");
  }

  private static final Object obj = new Object();

  @Test
  public void waitNotifyByInterrupt() throws Exception {
    // 创建线程
    Thread threadA = new Thread(() -> {
      try {
        System.out.println("begin");

        // 阻塞当前线程
        synchronized (obj) {
          obj.wait();
        }

        System.out.println("end");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    threadA.start();

    Thread.sleep(1000);

    System.out.println("begin interrupt threadA");
    threadA.interrupt();
    System.out.println("end interrupt threadA");
  }

  @Test
  public void waitNotifyByTimeout() throws Exception {
    // 创建线程
    Thread threadA = new Thread(() -> {
      try {
        System.out.println("begin");

        // 阻塞当前线程，2 秒后超时唤醒
        synchronized (obj) {
          obj.wait(2000);
        }

        System.out.println("end");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    threadA.start();

    threadA.join();

    System.out.println("main over");
  }

  @Test
  public void waitBlockForTimeout() throws Exception {
    // 创建线程
    Thread threadA = new Thread(() -> {
      try {
        System.out.println("threadA begin");

        // 阻塞当前线程，2 秒后超时唤醒
        synchronized (obj) {
          System.out.println("threadA try wait");
          obj.wait(3000);
        }

        System.out.println("threadA end");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    // 创建线程
    Thread threadB = new Thread(() -> {
      try {
        System.out.println("threadB begin");

        // 阻塞当前线程，2 秒后超时唤醒
        synchronized (obj) {
          System.out.println("threadB try wait");
          obj.wait(1000);
        }

        System.out.println("threadB end");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    threadA.start();
    threadB.start();

    threadA.join();
    threadB.join();

    System.out.println("main over");
  }

}
