package demo.api;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

  @Test
  public void removeIf() {
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(22);
    list.add(12);
    list.add(9);

    boolean result = list.removeIf(o -> o > 10);
    System.out.println("result:" + result);

    list.forEach(System.out::println);
  }

  @Test
  public void replaceAll() {
    List<Integer> list = new ArrayList<>();
    list.add(1);
    list.add(22);
    list.add(12);
    list.add(9);

    list.replaceAll(o -> o + 1);

    list.forEach(System.out::println);
  }

}
