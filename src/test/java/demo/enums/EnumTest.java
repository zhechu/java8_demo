package demo.enums;

import org.junit.Test;

public class EnumTest {

  @Test
  public void valueOf() {
    Status status = Status.valueOf("NEW");
    System.out.println(status);
  }

}

enum Status {

  NEW(1, "新"),
  START(2, "开始"),
  ;

  private int value;

  private String name;

  Status(int value, String name) {
    this.value = value;
    this.name = name;
  }

  public int getValue() {
    return value;
  }

  public String getName() {
    return name;
  }

  @Override
  public String toString() {
    return "Status{" +
        "value=" + value +
        ", name='" + name + '\'' +
        '}';
  }

}