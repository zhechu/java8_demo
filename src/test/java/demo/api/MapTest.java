package demo.api;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

  /**
   * 默认容量为 16
   */
  @Test
  public void defaultInitialCapacity() {
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < 20; i++) {
      map.put(i, i);
    }
  }

  @Test
  public void initialCapacity() {
    int initialCapacity = 50;
    Map<Integer, Integer> map = new HashMap<>(initialCapacity);

    for (int i = 0; i < initialCapacity; i++) {
      map.put(i, i);
    }
  }

  @Test
  public void expectedSize() {
    int initialCapacity = 50;
    Map<Integer, Integer> map = Maps.newHashMapWithExpectedSize(initialCapacity);

    for (int i = 0; i < initialCapacity; i++) {
      map.put(i, i);
    }
  }

  /**
   * 统计一个字符串中各个字母出现的频率
   */
  @Test
  public void compute() {
    String str = "hello java, i am vary happy! nice to meet you";

    int len = str.length();
    HashMap<Character, Integer> result = Maps.newHashMapWithExpectedSize(len);
    for (int i = 0; i < len; i++) {
      result.compute(str.charAt(i), (k, v) -> {
        if (v == null) {
          v = 1;
        } else {
          v += 1;
        }
        return v;
      });
    }

    System.out.println(result);
  }

}
