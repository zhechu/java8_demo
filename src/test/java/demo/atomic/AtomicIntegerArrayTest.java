package demo.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class AtomicIntegerArrayTest {

  @Test
  public void getAndIncrement() {
    AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
    atomicIntegerArray.getAndIncrement(1);
    System.out.println(atomicIntegerArray.get(1));
  }

}
