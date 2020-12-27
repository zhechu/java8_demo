package demo.thread;

import org.junit.Test;

public class ThreadLocalTest {

  private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

  @Test
  public void threadLocal() {
    // 设置线程变量
    threadLocal.set("hello world");

    // 启动子线程
    Thread thread = new Thread(() -> {
      // 子线程输出线程变量的值
      System.out.println(Thread.currentThread() + ":" + threadLocal.get());
    });

    thread.start();

    // 主线程输出线程变量的值
    System.out.println(Thread.currentThread() + ":" + threadLocal.get());
  }

  @Test
  public void set() {
    ThreadLocal<String> threadLocal = new ThreadLocal<>();

    for (int i = 0; i < 100; i++) {
      threadLocal.set(String.valueOf(i));
    }

    System.out.println(threadLocal.get());
  }

  private static ThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

  /**
   * 支持继承
   */
  @Test
  public void inheritableThreadLocal() {
    // 设置线程变量
    inheritableThreadLocal.set("hello world");

    // 启动子线程
    Thread thread = new Thread(() -> {
      // 子线程输出线程变量的值
      System.out.println(Thread.currentThread() + ":" + inheritableThreadLocal.get());
    });

    thread.start();

    // 主线程输出线程变量的值
    System.out.println(Thread.currentThread() + ":" + inheritableThreadLocal.get());
  }

}
