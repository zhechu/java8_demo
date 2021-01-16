package demo.lambda;

import org.junit.Test;

import java.util.function.Function;

public class FunctionTest {

  @Test
  public void compose() {
    Function<Integer, Integer> funcA = i -> i * 2;
    Function<Integer, Integer> funcB = i -> i * i;

    System.out.println(funcA.apply(4));
    System.out.println(funcB.apply(4));

    // funcB 执行后再执行 funcA
    System.out.println(funcA.compose(funcB).apply(4));
  }

  @Test
  public void andThen() {
    Function<Integer, Integer> funcA = i -> i * 2;
    Function<Integer, Integer> funcB = i -> i * i;

    System.out.println(funcA.apply(4));
    System.out.println(funcB.apply(4));

    // funcA 执行后再执行 funcB
    System.out.println(funcA.andThen(funcB).apply(4));
  }

}
