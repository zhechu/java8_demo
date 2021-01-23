package demo.api;

import org.junit.Test;

import java.util.Random;

public class RandomTest {

  @Test
  public void ints() {
    new Random().ints(0, 10)
        .distinct()
        .limit(10)
        .forEach(System.out::println);
  }

}
