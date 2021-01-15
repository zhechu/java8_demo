package demo.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class SystemStreamTest {

  public static void main(String[] args) throws IOException {
//    inTest();

    outTest();
  }

  public static void inTest() throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    char c;
    do {
      c = (char) bufferedReader.read();
      System.out.println(c);
    } while (c != 'q');
  }

  public static void outTest() {
    PrintWriter printWriter = new PrintWriter(System.out, true);
    printWriter.println("This is a string");
    printWriter.println(-7);
    printWriter.println(0.56);
  }

}
