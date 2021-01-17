package demo.api;

import org.junit.Test;

import java.util.IdentityHashMap;
import java.util.Map;

public class IdentityHashMapTest {

  @Test
  public void put() {
    Map<String, String> identityHashMap = new IdentityHashMap<>();
    String a = new String("a");
    identityHashMap.put(a, "1");
    identityHashMap.put(new String("a"), "2");
    identityHashMap.put(new String("a"), "3");
    System.out.println(identityHashMap.size());

    System.out.println(identityHashMap.get(new String("a")));
    System.out.println(identityHashMap.get(a));
  }

}
