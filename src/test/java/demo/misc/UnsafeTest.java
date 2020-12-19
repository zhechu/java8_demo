package demo.misc;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {

  @Test
  public void park() {
    System.out.println("begin ...");

    unsafe.park(false, 3000000000L);

    System.out.println("end ...");
  }

  @Test
  public void compareAndSwapLong() {
    System.out.println("start ....");

    UnsafeTest unsafeTest = new UnsafeTest();

    boolean success = unsafe.compareAndSwapLong(unsafeTest, stateOffset, 0, 1);

    System.out.println(success);

    System.out.println("end ....");
  }

  static Unsafe unsafe;

  static final long stateOffset;

  private volatile long state = 0;

  static {
    try {
      Field field = Unsafe.class.getDeclaredField("theUnsafe");
      field.setAccessible(true);
      unsafe = (Unsafe) field.get(null);
      stateOffset = unsafe.objectFieldOffset(UnsafeTest.class.getDeclaredField("state"));
    } catch (Exception ex) {
      System.out.println(ex.getLocalizedMessage());
      throw new Error(ex);
    }
  }

}
