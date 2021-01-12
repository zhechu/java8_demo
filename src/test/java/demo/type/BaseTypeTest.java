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
//    int b = 1000000000;
//    int result = a * b;
//    System.out.println(result);

    int c = 1;
    System.out.println(c);

    c = c + a;
    System.out.println(c);

    c += a;
    System.out.println(c);

    byte b = 1;
    byte i = 1;
    i += b;

    int bb = 0b0101;
    System.out.println(~bb);

    int a1 = 1;
    int a2 = 2;
    a1 ^= a2;
    a2 ^= a1;
    a1 ^= a2;
    System.out.println(a1 + "," + a2);
  }

  @Test
  public void floatTest() {
    float a = 1.1234567890F;
    float b = 1.1234567F;
    System.out.println(a);
    System.out.println(b);

    float f = 123.23E5F;
    System.out.println(f);

    float i = 0x12.23P2F;
    System.out.println(i);
  }

  @Test
  public void doubleTest() {
    double a = 1.12345678901234567890;
    double b = 1.123456789012345;
    System.out.println(a);
    System.out.println(b);
  }

  @Test
  public void stringTest() {
    String a = "\u0061";
    System.out.println(a);
  }

  /**
   * 不规则多维数组
   */
  @Test
  public void arrayTest() {
    int[][] array = new int[5][];
    array[0] = new int[1];
    array[1] = new int[2];
    array[2] = new int[3];
    array[3] = new int[4];
    array[4] = new int[5];
  }

}
