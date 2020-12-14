package demo.thread;

import org.junit.Test;

public class ObjectDaemonTest {

  public static void main(String[] args) {
    // 创建线程
    Thread threadA = new Thread(() -> {
      for (;;) {}
    });

    // 设置为守护线程（当所有用户线程退出后，则守护线程立即退出）
    threadA.setDaemon(true);
    threadA.start();

    System.out.println("main over");
  }

}
