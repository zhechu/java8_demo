package demo.lambda;

import org.junit.Test;

import java.util.function.Consumer;

public class ConsumerTest {

  @Test
  public void apply() {
    Consumer<Integer> consumer = n -> System.out.println(n + 1);
    consumer.accept(1);
  }

}
