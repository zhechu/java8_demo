package demo.generic;

public class BridgeMethodTest {

  public static void main(String[] args) {
    GenTwo genTwo = new GenTwo("hello");
    System.out.println(genTwo.getT());
  }

}

class Gen<T> {

  protected T t;

  public Gen(T t) {
    this.t = t;
  }

  public T getT() {
    System.out.println("Gen<T>");
    return t;
  }

}

class GenTwo extends Gen<String> {

  public GenTwo(String s) {
    super(s);
  }

  public String getT() {
    System.out.println("GenTwo");
    return t;
  }

}

