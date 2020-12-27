package demo.locks;

import org.junit.Test;

public class SynchronizedTest {

  /**
   * 实例锁，互斥
   */
  @Test
  public void instanceLock() throws InterruptedException {
    System.out.println("main start");

    Person person = new Person();

    Thread threadA = new Thread(() -> {
      person.hello();
    });

    Thread threadB = new Thread(() -> {
      person.hi();
    });

    threadA.start();
    threadB.start();
    threadA.join();
    threadB.join();

    System.out.println("main end");
  }

  /**
   * 实例和类锁，不同锁，不互斥
   */
  @Test
  public void instanceAndClassLock() throws InterruptedException {
    System.out.println("main start");

    Person person = new Person();

    Thread threadA = new Thread(() -> {
      person.hello();
    });

    Thread threadB = new Thread(() -> {
      Person.ok();
    });

    threadA.start();
    threadB.start();
    threadA.join();
    threadB.join();

    System.out.println("main end");
  }

}

class Person {

  public synchronized void hello() {
    System.out.println("hello start");
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("hello end");
  }

  public synchronized void hi() {
    System.out.println("hi start");
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("hi end");
  }

  public static void ok() {
    System.out.println("ok start");
    try {
      Thread.sleep(3000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("ok end");
  }

}