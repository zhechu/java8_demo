package demo.api;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteArrayInputStreamTest {

  @Test
  public void readTwice() throws IOException {
    String name = "abskdfjsdfjkdf";
    InputStream inputStream = new ByteArrayInputStream(name.getBytes());

    for (int i = 0; i < 2; i++) {
      int c;
      while ((c = inputStream.read()) != -1) {
        if (i == 0) {
          System.out.print((char) c);
        } else {
          System.out.print(Character.toUpperCase((char) c));
        }
      }
      System.out.println();
      inputStream.reset();
    }
  }

}
