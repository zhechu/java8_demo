package demo.locks;

import org.junit.Test;

public class WaitAndNotifyTest {

  @Test
  public void waitTest() throws InterruptedException {
    Object lock = new Object();

    System.out.println("main start");

    Thread threadA = new Thread(() -> {
      System.out.println("threadA start");

      synchronized (lock) {
        try {
          lock.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      System.out.println("threadA end");
    });

    Thread threadB = new Thread(() -> {
      System.out.println("threadB start");

      synchronized (lock) {
        try {
          Thread.sleep(3000);

          lock.notify();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

      System.out.println("threadB end");
    });

    threadA.start();
    threadB.start();
    threadA.join();
    threadB.join();

    System.out.println("main end");
  }

}