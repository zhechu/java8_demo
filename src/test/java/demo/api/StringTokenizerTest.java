package demo.api;

import org.junit.Test;

import java.util.StringTokenizer;

public class StringTokenizerTest {

  @Test
  public void nextToken() {
    String str = "a, b, c, d, e : hello : world";
    StringTokenizer stringTokenizer = new StringTokenizer(str, ", ");

    int count = 0;
    while (stringTokenizer.hasMoreTokens()) {
      if (count > 3) {
        String token = stringTokenizer.nextToken(":");
        System.out.println(token);
      } else {
        String token = stringTokenizer.nextToken();
        System.out.println(token);
      }

      count ++;
    }
  }

}
