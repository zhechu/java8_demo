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

}
