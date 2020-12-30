package demo.locks;

import org.junit.Test;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class ExchangerTest {

  /**
   * 在线交易
   * @throws InterruptedException
   */
  @Test
  public void exchange() throws InterruptedException {
    ExecutorService executor = Executors.newCachedThreadPool();

    Exchanger<String> exchanger = new Exchanger();
    executor.execute(() -> nbaTrade("克拉克森，小拉里南斯", exchanger));
    executor.execute(() -> nbaTrade("格里芬", exchanger));
    executor.execute(() -> nbaTrade("哈里斯", exchanger));
    executor.execute(() -> nbaTrade("以赛亚托马斯，弗莱", exchanger));

    Thread.sleep(10000);

    executor.shutdown();
  }

  private static void nbaTrade(String data, Exchanger<String> exchanger) {
    try {
      data = Thread.currentThread().getName() + "[" + data + "]";
      System.out.println(Thread.currentThread().getName() + "在交易截止之前把{" + data + "}交易出去");
      Thread.sleep(ThreadLocalRandom.current().nextLong(100));

      String result = exchanger.exchange(data);
      System.out.println(Thread.currentThread().getName() + "交易得到{" + result + "}");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
