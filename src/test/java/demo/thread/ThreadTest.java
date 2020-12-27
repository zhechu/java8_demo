package demo.thread;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程创建方式
 * <br/>
 * 1.继承 {@link Thread} 类，方便传参
 * <br/>
 * 2.实现 {@link Runnable} 接口，任务与代码隔离
 * <br/>
 * 3.实现 {@link Callable} 接口，有返回值
 */
public class ThreadTest {

  @Test
  public void createThreadByThread() throws Exception {
    // 创建线程
    MyThread myThread = new MyThread();

    // 启动线程
    myThread.start();

    // 待 myThread 运行终止再执行后面的操作
    myThread.join();

    System.out.println(Thread.currentThread().getName() + ": over");
  }

  @Test
  public void createThreadByRunnable() throws Exception {
    // 创建线程
    RunnableTask runnableTask = new RunnableTask();
    Thread myThread1 = new Thread(runnableTask);
    Thread myThread2 = new Thread(runnableTask);

    // 启动线程
    myThread1.start();
    myThread2.start();

    // 待 myThread 运行终止再执行后面的操作
    myThread1.join();
    myThread2.join();

    System.out.println(Thread.currentThread().getName() + ": over");
  }

  @Test
  public void createThreadByCallable() throws Exception {
    // 创建线程
    FutureTask<String> futureTask = new FutureTask(new CallerTask());
    Thread myThread = new Thread(futureTask);

    // 启动线程
    myThread.start();

    // 等待任务执行完毕并返回执行结果
    String result = futureTask.get();
    System.out.println("MyThread exec result:" + result);

    System.out.println(Thread.currentThread().getName() + ": over");
  }

  public void exit() {
    System.out.println("main start");

    Thread thread = new Thread(() -> {
      for (;;) {
        System.out.println("child run ...");
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    });

    thread.setDaemon(true);
    thread.start();

    System.out.println("main exit");
  }

  /**
   * 测试子线程未退出则进程不退出
   * @param args
   */
  public static void main(String[] args) {
    new ThreadTest().exit();
  }

  /**
   * 线程被锁阻塞后，调用打断方法不会抛出异常
   * 能够被中断的阻塞称为轻量级阻塞，对应的线程状态是WAITING或者TIMED_WAITING；
   * 而像synchronized这种不能被中断的阻塞称为重量级阻塞，对应的状态是BLOCKED
   */
  @Test
  public void lockAndInterrupt() {
    Lock lock = new ReentrantLock();

    System.out.println("main start");

    Thread thread = new Thread(() -> {
      lock.lock();
      try {
        System.out.println("child run ...");
      } finally {
        lock.unlock();
      }
    });

    thread.start();

    lock.lock();
    try {
      System.out.println("main run ...");
      Thread.sleep(1000);
      thread.interrupt();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();
    }

    System.out.println("main exit");
  }

  /**
   * 线程调用 park 方法后被中断，不会抛出异常，但会马上从阻塞状态变为可运行状态
   */
  @Test
  public void parkAndInterrupt() {
    System.out.println("main start");

    Thread thread = new Thread(() -> {
      try {
        LockSupport.park(5000000000L);
      } catch (Exception e) {
        e.printStackTrace();
      }
      System.out.println("child run ...");
    });

    thread.start();

    try {
      System.out.println("main run ...");
      Thread.sleep(1000);
      thread.interrupt();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("main exit");
  }

}

class MyThread extends Thread {

  @Override
  public void run() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(Thread.currentThread().getName() + ": I am a child thread");
  }

}

class RunnableTask implements Runnable {

  @Override
  public void run() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println(Thread.currentThread().getName() + ": I am a child thread");
  }

}

class CallerTask implements Callable<String> {

  @Override
  public String call() throws Exception {
    Thread.sleep(1000);

    System.out.println(Thread.currentThread().getName() + ": I am a child thread");

    return "ok";
  }

}