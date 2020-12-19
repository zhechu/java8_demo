package demo.cache;

import org.junit.Test;
import sun.misc.Contended;

/**
 * CPU缓存行填充，避免伪共享
 */
public class ContendedTest {

  @Contended
  volatile long a;

  @Contended
  volatile long b;

  /**
   * 开启 @Contended 注解 JVM 参数 -XX:-RestrictContended
   * 默认宽度是 128 字节，可以使用 -XX:ContendedPaddingWidth 自定义宽度
   * 使用 –XX:+PrintFieldLayout 参数可以查看细节（只是在调试版JDK有效）
   * @throws Exception
   */
  @Test
  public void foreach() throws Exception {
    int count = 100000000;
    ContendedTest contendedTest = new ContendedTest();
    Thread threadA = new Thread(() -> {
      for (int i = 0; i < count; i++) {
        contendedTest.a = i;
      }
    });

    Thread threadB = new Thread(() -> {
      for (int i = 0; i < count; i++) {
        contendedTest.b = i;
      }
    });

    final long startTime = System.currentTimeMillis();
    threadA.start();
    threadB.start();
    threadA.join();
    threadB.join();
    System.out.println((System.currentTimeMillis() - startTime));
  }

}
