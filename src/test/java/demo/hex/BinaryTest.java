package demo.hex;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class BinaryTest {

  /**
   * 转换
   */
  @Test
  public void convert() {
    for (int i = -1; i > -10; i--) {
      print(i);
      System.out.println();
    }
  }

  /**
   * 按位取反
   */
  @Test
  public void negate() {
    int a = 1;
    System.out.println(~a);
    print(~a);

    System.out.println();

    a = -1;
    System.out.println(~a);
    print(~a);
  }

  @Test
  public void add() {
//    AtomicInteger atomicInteger = new AtomicInteger(-1);
//    System.out.println(atomicInteger.get());
//    print(atomicInteger.get());
//
//    System.out.println();
//
//    int result = atomicInteger.incrementAndGet();
//    System.out.println(result);
//    print(result);
//
//    System.out.println();

    int r = -1 << (Integer.SIZE - 3);
    System.out.println(r);
    print(r);

    System.out.println();

    System.out.println(r | 0);
    print(r | 0);

    System.out.println();

    r += 1;
    System.out.println(r);
    print(r);
  }

  private void print(int a) {
    for (int i = 0; i < 32; i++) {
      int t = (a & 0x80000000 >>> i) >>> (31 - i);
      System.out.print(t);
    }
  }

}
