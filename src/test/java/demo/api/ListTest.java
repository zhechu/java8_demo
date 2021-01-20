package demo.api;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
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

  /**
   * 默认容量为 10
   */
  @Test
  public void defaultInitialCapacity() {
    List<Integer> list = new ArrayList<>();

    for (int i = 0; i < 20; i++) {
      list.add(i);
    }
  }

  @Test
  public void initialCapacity() {
    int initialCapacity = 20;
    List<Integer> list = new ArrayList<>(initialCapacity);

    for (int i = 0; i < 20; i++) {
      list.add(i);
    }
  }

  @Test
  public void forEachRemaining() {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(i);
    }

    Iterator<Integer> iterator = list.iterator();
    while (iterator.hasNext()) {
      Integer i = iterator.next();
      if (i > 5) {
        break;
      }
      System.out.println(i);
    }

    System.out.println("========================");

    iterator.forEachRemaining(System.out::println);
  }

}
