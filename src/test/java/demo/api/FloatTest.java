package demo.api;

import org.junit.Test;

public class FloatTest {

  @Test
  public void toHexString() {
    Float f = 10f;
    String s = Float.toHexString(f);
    System.out.println(s);
  }

  @Test
  public void isInfinite() {
    Float f1 = new Float(1 / 0.);
    Float f2 = new Float(0 / 0.);

    System.out.println(f1 + ":" + f1.isInfinite() + ", " + f1.isNaN());
    System.out.println(f2 + ":" + f2.isInfinite() + ", " + f2.isNaN());
  }

}
