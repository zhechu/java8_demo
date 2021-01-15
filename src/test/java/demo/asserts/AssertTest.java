package demo.asserts;

import org.junit.Test;

public class AssertTest {

  @Test
  public void assertTest() {
    for (int i = 0; i < 100; i++) {
      assert i == 10: "自定义异常信息";
    }
  }

  /**
   * JVM 需加上 -ea 参数
   * @param args
   */
  public static void main(String[] args) {
    for (int i = 0; i < 100; i++) {
      assert i == 10: "自定义异常信息";
    }
  }

}
