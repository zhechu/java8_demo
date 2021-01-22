package demo.api;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class CollectionsTest {

  @Test
  public void addAll() {
    Integer[] array = {1, 3, 2, 4, 5};
    List<Integer> list = new ArrayList<>();
    Collections.addAll(list, array);

    list.forEach(System.out::println);
  }

  @Test
  public void frequency() {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(i);
    }
    list.add(2);
    list.add(2);

    int frequency = Collections.frequency(list, 2);
    System.out.println(frequency);
  }

  @Test
  public void shuffle() {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      list.add(i);
    }

    Collections.shuffle(list);

    list.forEach(System.out::println);
  }

  /**
   * 自定义洗牌算法
   */
  @Test
  public void shuffleByCustom() {
    List<Integer> list = IntStream.range(0, 10).boxed().collect(Collectors.toList());
    list.forEach(System.out::println);

    int[] randomArray = new Random().ints(0, 10)
        .distinct()
        .limit(10).toArray();
    List<Integer> resultList = new ArrayList<>(list.size());
    for (int i = 0; i < randomArray.length; i++) {
      resultList.add(list.get(randomArray[i]));
    }

    System.out.println("=====================");

    resultList.forEach(System.out::println);
  }

}
