package demo.api;

import org.junit.Test;

import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetTest {

  @Test
  public void headSet() {
    SortedSet<Integer> sortedSet = new TreeSet<>();
    for (int i = 0; i < 100; i++) {
      sortedSet.add(i);
    }

    SortedSet<Integer> resultSet = sortedSet.headSet(10);
    resultSet.forEach(System.out::println);

    System.out.println(sortedSet.size());
  }

}
