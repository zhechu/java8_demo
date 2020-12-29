package demo.locks;

import org.junit.Test;

public class VolatileTest {

  @Test
  public void happenBefore() throws InterruptedException {
    for (int i = 0; i < 1000; i++) {
      setHappenBeforeGet();
    }

//    Thread.sleep(2 * 60 * 1000);
  }

  private static void setHappenBeforeGet() throws InterruptedException {
    A a = new A();

    Thread threadA = new Thread(() -> {
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      a.set();
    });

    Thread threadB = new Thread(() -> {
      try {
        Thread.sleep(3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      int i = a.get();
      if (i != 5) {
        throw new RuntimeException();
      }
      System.out.println("threadB get a:" + i);
    });

    threadB.start();
    threadA.start();

    threadB.join();
    threadA.join();
  }

}

class A {

  private int a = 0;

//  private volatile int c = 0;

  public void set() {
    a = 5;
//    c = 1;
  }

  public int get() {
//    int d = c;
    return a;
  }

}