package demo.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.BinaryOperator;

public class BinaryOperatorTest {

  @Test
  public void apply() {
    BinaryOperator<Integer> add = (n1, n2) -> n1 + n2;
    System.out.println(add.apply(3, 4));
  }

  /**
   * 获取更小的值
   */
  @Test
  public void minBy() {
    BinaryOperator<Integer> bo = BinaryOperator.minBy(Comparator.naturalOrder());
    System.out.println(bo.apply(2, 3));
  }

  /**
   * 获取更大的值
   */
  @Test
  public void maxBy() {
    BinaryOperator<Integer> bo = BinaryOperator.maxBy(Comparator.naturalOrder());
    System.out.println(bo.apply(2, 3));
  }

}
