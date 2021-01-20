package demo.api;

import lombok.*;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;

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

  /**
   * 实现按分值排序
   */
  @Test
  public void scoreSort() {
    TreeSet<CacheData> treeSet = new TreeSet<>((o1, o2) -> {
      if (o1.getScore() == o2.getScore()) {
        return 0;
      }
      return o1.getScore() > o2.getScore() ? 1 : -1;
    });

    Random random = new Random();
    for (int i = 0; i < 100; i++) {
      treeSet.add(new CacheData(String.valueOf(i), random.nextDouble()));
    }

    System.out.println("=================================");

    treeSet.forEach(System.out::println);

    // 覆盖
    treeSet.add(new CacheData("99", random.nextDouble()));

    System.out.println("=================================");

    treeSet.stream().filter(o -> o.getId().equals("99")).forEach(System.out::println);

  }

  @Test
  public void set() {
    Set<CacheData> hashSet = new HashSet<>();

    Random random = new Random();
    for (int i = 0; i < 100; i++) {
      hashSet.add(new CacheData(String.valueOf(i), random.nextDouble()));
    }

    System.out.println("=================================");

    hashSet.forEach(System.out::println);

    // 覆盖
    hashSet.add(new CacheData("99", random.nextDouble()));

    System.out.println("=================================");

    hashSet.stream().filter(o -> o.getId().equals("99")).forEach(System.out::println);
  }

  /**
   * 实现根据分值排序集合并使用分页查询
   */
  @Test
  public void sortedSet() {
    Set<CacheData> sortedSet = new TreeSet<>((o1, o2) -> {
      if (o1.getScore() == o2.getScore()) {
        if (o1.getId().equals(o2.getId())) {
          return 0;
        }

        return o1.getId().compareTo(o2.getId());
      }

      return o1.getScore() > o2.getScore() ? 1 : -1;
    });
    Map<String, Double> sortedMap = new HashMap<>();

    Random random = new Random();
    for (int i = 0; i < 100; i++) {
      CacheData cacheData = new CacheData(String.valueOf(i), random.nextDouble());
      sortedSet.add(cacheData);
      sortedMap.put(cacheData.getId(), cacheData.getScore());
    }

    int page = 1;
    int pageSize = 10;
    List<CacheData> resultList = sortedSet.stream()
        .skip((page - 1) * pageSize)
        .limit(pageSize)
        .collect(Collectors.toList());
    resultList.forEach(System.out::println);

    System.out.println("=================================");

    for (int i = 100; i < 110; i++) {
      CacheData cacheData = new CacheData(String.valueOf(i), 100);
      sortedSet.add(cacheData);
      sortedMap.put(cacheData.getId(), cacheData.getScore());
    }

    sortedSet.forEach(System.out::println);

    // 更新 99 的分值
    String id = "99";
    CacheData cacheData = new CacheData(id, sortedMap.get(id));
    sortedSet.remove(cacheData);
    Double score = random.nextDouble();
    cacheData.setScore(score);
    sortedSet.add(cacheData);
    sortedMap.put(id, score);

    System.out.println("=================================");

    sortedSet.stream().filter(o -> o.getId().equals("99")).forEach(System.out::println);

    System.out.println("=================================");

    sortedSet.stream().filter(o -> o.getScore() >= 100).forEach(System.out::println);
  }

  @Test
  public void concurrentSkipListSet() {
    Set<String> set = new ConcurrentSkipListSet<>(String::compareTo);
    for (int i = 0; i < 100; i++) {
      set.add(String.valueOf(i));
    }

    set.forEach(System.out::println);

    // 覆盖
    set.add("99");

    System.out.println("=================================");

    set.forEach(System.out::println);

    System.out.println("=================================");

    set.stream().filter(o -> o.equals("99")).forEach(System.out::println);
  }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class CacheData {

  private String id;

  private double score;

}