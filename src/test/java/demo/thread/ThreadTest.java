package demo.thread;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

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