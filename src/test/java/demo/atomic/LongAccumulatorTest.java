package demo.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.function.LongBinaryOperator;

/**
 * 原子累加器
 */
public class LongAccumulatorTest {

  /**
   * 等价于 {@link java.util.concurrent.atomic.LongAdder}
   */
  @Test
  public void equivalentToLongAddr() throws InterruptedException {
    LongAccumulator longAccumulator = new LongAccumulator((left, right) -> left + right, 0);

    int count = 10000;
    Thread threadA = new Thread(() -> {
      for (int i = 0; i < count; i++) {
        longAccumulator.accumulate(1);
      }
    });

    Thread threadB = new Thread(() -> {
      for (int i = 0; i < count; i++) {
        longAccumulator.accumulate(1);
      }
    });

    threadA.start();
    threadB.start();
    threadA.join();
    threadB.join();

    System.out.println(longAccumulator.get());
  }

}
