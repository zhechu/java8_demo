package demo.api;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

public class SpliteratorTest {

  @Test
  public void tryAdvance() {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(i);
    }

    Spliterator<Integer> spliterator = list.spliterator();
    while (spliterator.tryAdvance(System.out::println));

    System.out.println("========================");

    spliterator.forEachRemaining(System.out::println);
  }

}
