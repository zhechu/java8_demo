package demo.cache;

import org.junit.Test;

/**
 * 使用临时变量提高性能
 */
public class VariableTest {

  @Test
  public void test() {
    VariableTest test = new VariableTest();
    long count = 10 * 1000 * 1000 * 1000L;
    test.tempAccess(count);
    test.instanceAccess(count);
    test.staticAccess(count);
  }

  /**
   * 成员变量
   */
  private long instVar;

  /**
   * 静态变量
   */
  private static long staticVar;

  /**
   * 存取类方法中的临时变量
   * @param val
   */
  void tempAccess(long val) {
    long startTime = System.currentTimeMillis();
    long j = 0;// 临时变量
    for (long i = 0; i < val; i++) {
      j += 1;
    }
    long endTime = System.currentTimeMillis();
    System.out.println("temp var: " + (endTime - startTime) + " milli seconds");
  }

  /**
   * 存取类的成员变量
   * @param val
   */
  void instanceAccess(long val) {
    long startTime = System.currentTimeMillis();
    long tmp=instVar;
    for (long i = 0; i < val; i++) {
      tmp += 1;
//      instVar += 1;
    }
    instVar=tmp;
    long endTime = System.currentTimeMillis();
    System.out.println("instance var: " + (endTime - startTime) + " milli seconds");
  }

  /**
   * 存取类的 static 变量
   * @param val
   */
  void staticAccess(long val) {
    long startTime = System.currentTimeMillis();
    long tmp=staticVar;
    for (long i = 0; i < val; i++) {
      tmp += 1;
//      staticVar += 1;
    }
    staticVar=tmp;
    long endTime = System.currentTimeMillis();
    System.out.println("static var: " + (endTime - startTime) + " milli seconds");
  }

}
