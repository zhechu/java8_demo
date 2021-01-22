package demo.api;

import org.junit.Test;

import java.util.Arrays;
import java.util.TimeZone;

public class TimeZoneTest {

  @Test
  public void getAvailableIDs() {
    String[] availableIDs = TimeZone.getAvailableIDs();
    Arrays.stream(availableIDs).forEach(System.out::println);
  }

}
