package demo.lambda;

import org.junit.Test;

import java.util.function.Supplier;

public class SupplierTest {

  @Test
  public void apply() {
    Supplier supplier = () -> 1;
    System.out.println(supplier.get());
  }

}
