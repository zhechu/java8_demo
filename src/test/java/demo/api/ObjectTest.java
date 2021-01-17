package demo.api;

import org.junit.Test;

public class ObjectTest {

  @Test
  public void cloneTest() throws CloneNotSupportedException {
    A a = new A();
    a.cloneA();
  }

}

class A implements Cloneable {

  public A cloneA() throws CloneNotSupportedException {
    return (A) super.clone();
  }

}