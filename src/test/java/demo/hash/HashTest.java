package demo.hash;

import org.junit.Test;

public class HashTest {

  @Test
  public void t() {
    int hashCode = "192.168.1.0:1111".hashCode();
    System.out.println(hashCode);
  }

}
