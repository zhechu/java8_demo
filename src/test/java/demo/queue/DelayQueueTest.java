package demo.queue;

import lombok.ToString;
import org.junit.Test;

import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayQueueTest {

  @Test
  public void delayQueue() {
    DelayQueue<DelayTask> delayQueue = new DelayQueue<>();
    Random random = new Random();
    for (int i = 0; i < 10; i++) {
      delayQueue.offer(new DelayTask(random.nextInt(500), "task:" + i));
    }

    DelayTask delayTask = null;
    try {
      while ((delayTask = delayQueue.take()) != null) {
        System.out.println(delayTask);
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}

@ToString
class DelayTask implements Delayed {

  /**
   * 延迟时间，单位：毫秒
   */
  private final long delayTime;

  /**
   * 过期时间，单位：毫秒
   */
  private final long expire;

  /**
   * 队列名称
   */
  private final String taskName;

  public DelayTask(long delayTime, String taskName) {
    this.delayTime = delayTime;
    this.expire = System.currentTimeMillis() + delayTime;
    this.taskName = taskName;
  }

  @Override
  public long getDelay(TimeUnit unit) {
    return unit.convert(this.expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
  }

  @Override
  public int compareTo(Delayed o) {
    return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
  }

}