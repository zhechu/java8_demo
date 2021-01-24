package demo.api;

import org.junit.Test;

public class IntegerTest {

  /**
   * 求二进制中1的个数
   */
  @Test
  public void bitCount() {
    System.out.println(Integer.bitCount(10));
  }

  /**
   * 求倍数
   */
  @Test
  public void getMultiple() {
    int mod = 16;
    int target = 16;
    int bitCount = Integer.bitCount(mod - 1);
    System.out.println("bitCount:" + bitCount);
    int result = target >> bitCount;
    System.out.println("result:" + result);
  }

  /**
   * 规范时间轮每轮的大小
   */
  @Test
  public void normalizeTicksPerWheel() {
    for (int i = 0; i < 100; i++) {
      System.out.println("ticksPerWheel:" + i + ", normalizedTicksPerWheel:" + netty(i));
      System.out.println("ticksPerWheel:" + i + ", normalizedTicksPerWheel:" + hashMap(i));
      System.out.println("ticksPerWheel:" + i + ", normalizedTicksPerWheel:" + integer(i));
      System.out.println("================================");
    }
  }

  private int integer(int ticksPerWheel) {
    if (ticksPerWheel <= 1) {
      return ticksPerWheel;
    }

    int n = ticksPerWheel - 1;

    int normalizedTicksPerWheel = Integer.highestOneBit(n);
    normalizedTicksPerWheel <<= 1;
    return normalizedTicksPerWheel;
  }

  private int hashMap(int ticksPerWheel) {
    if (ticksPerWheel <= 1) {
      return ticksPerWheel;
    }

    int n = ticksPerWheel - 1;
    n |= n >>> 1;
    n |= n >>> 2;
    n |= n >>> 4;
    n |= n >>> 8;
    n |= n >>> 16;
    int normalizedTicksPerWheel = n + 1;
    return normalizedTicksPerWheel;
  }

  private int netty(int ticksPerWheel) {
    if (ticksPerWheel <= 1) {
      return ticksPerWheel;
    }

    int normalizedTicksPerWheel = 1;
    while (normalizedTicksPerWheel < ticksPerWheel) {
      normalizedTicksPerWheel <<= 1;
    }
    return normalizedTicksPerWheel;
  }

}
