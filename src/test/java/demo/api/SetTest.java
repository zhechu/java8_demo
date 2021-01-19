package demo.api;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class SetTest {

  /**
   * 默认容量为 10
   */
  @Test
  public void defaultInitialCapacity() {
    Set<Integer> set = new HashSet<>();

    for (int i = 0; i < 20; i++) {
      set.add(i);
    }
  }

  @Test
  public void initialCapacity() {
    int initialCapacity = 20;
    Set<Integer> set = new HashSet<>(initialCapacity);

    for (int i = 0; i < 20; i++) {
      set.add(i);
    }
  }

  @Test
  public void expectedSize() {
    int initialCapacity = 50;
    Set<Integer> set = Sets.newHashSetWithExpectedSize(initialCapacity);

    for (int i = 0; i < initialCapacity; i++) {
      set.add(i);
    }
  }

}
