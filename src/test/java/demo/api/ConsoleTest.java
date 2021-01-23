package demo.api;

import java.io.Console;

public class ConsoleTest {

  public static void main(String[] args) {
    Console console = System.console();
    if (console == null) {
      return;
    }

    String str = console.readLine("Enter a string:");
    console.printf("Here is your string:%s\n", str);
  }

}
