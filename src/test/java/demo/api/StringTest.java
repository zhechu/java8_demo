package demo.api;

import org.junit.Test;

public class StringTest {

  @Test
  public void byteToString() {
    String input = "测试输入数据";
    byte[] bytes = input.getBytes();
    System.out.println(new String(bytes));
  }

  @Test
  public void regionMatches() {
    String s1 = "测试输入数据";
    String s2 = "输入数据";
    System.out.println(s1.regionMatches(4, s2, 2, 2));
  }

  @Test
  public void join() {
    System.out.println(String.join(",", "1", "2"));
  }

}
