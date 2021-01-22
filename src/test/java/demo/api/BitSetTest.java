package demo.api;

import org.junit.Test;

import java.util.BitSet;

public class BitSetTest {

  @Test
  public void setAndGet() {
    int id = 100;

    BitSet bitSet = new BitSet();
    bitSet.set(id);
    bitSet.set(200);

    System.out.println(bitSet.get(id));
    System.out.println(bitSet.get(200));

    System.out.println(bitSet.cardinality());

    bitSet.flip(id);
    System.out.println(bitSet.get(id));

    System.out.println(bitSet.toString());
  }

}
