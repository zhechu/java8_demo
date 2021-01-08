package demo.type;

import org.junit.Test;

public class BaseTypeTest {

  @Test
  public void byteTest() {
    byte a = 12;
    byte b = 13;
    byte result = (byte) (a + b);
    System.out.println(result);
  }

  @Test
  public void shortTest() {
    short a = 12;
    short b = 13;
    short result = (short) (a + b);
    System.out.println(result);
  }

  @Test
  public void intTest() {
    int a = 1000000000;
    int b = 1000000000;
    int result = a * b;
    System.out.println(result);
  }

  @Test
  public void floatTest() {
    float a = 1.1234567890F;
    float b = 1.1234567F;
    System.out.println(a);
    System.out.println(b);
  }

  @Test
  public void doubleTest() {
    double a = 1.12345678901234567890;
    double b = 1.123456789012345;
    System.out.println(a);
    System.out.println(b);
  }

}
