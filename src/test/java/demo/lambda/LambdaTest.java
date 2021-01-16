package demo.lambda;

import org.junit.Test;

public class LambdaTest {

  @Test
  public void foreach() {
    AI<Integer> ai = t -> t * 10;
    System.out.println(ai.get(11));

    AI<String> aiStr = t -> t + " ok";
    System.out.println(aiStr.get("hello"));
  }

  @Test
  public void classNameRef() {
    int count;
    HighTemp[] weekDayHighs = {
        new HighTemp(89),
        new HighTemp(82),
        new HighTemp(90),
        new HighTemp(89),
        new HighTemp(89),
        new HighTemp(91),
        new HighTemp(84),
        new HighTemp(83)
    };

    count = counter(weekDayHighs, HighTemp::sameTemp, new HighTemp(89));
    System.out.println(count + " days had a high of 89");

    count = counter(weekDayHighs, HighTemp::lessThanTemp, new HighTemp(91));
    System.out.println(count + " days had a high of less than 91");
  }

  static <T> int counter(T[] ts, MyFunc<T> f, T t) {
    int count = 0;
    for (int i = 0; i < ts.length; i++) {
      if (f.func(ts[i], t)) {
        count ++;
      }
    }
    return count;
  }

  /**
   * 泛型方法中的引用
   */
  @Test
  public void genericMethodRef() {
    Integer[] ts = {1, 2, 3, 4, 2, 3, 4, 4, 5};
    String[] strs = {"One", "Two", "Three", "Two"};

    int count;
    count = op(ts, ArrayOps::<Integer>countMatching, 4);
    System.out.println("ts contains " + count + " 4s");

    count = op(strs, ArrayOps::<String>countMatching, "Two");
    System.out.println("strs contains " + count + " Twos");
  }

  static <T> int op(T[] ts, Func<T> f, T t) {
    return f.func(ts, t);
  }

}

interface Func<T> {

  int func(T[] ts, T t);

}

class ArrayOps {

  static <T> int countMatching(T[] ts, T t) {
    int count = 0;
    for (int i = 0; i < ts.length; i++) {
      if (ts[i] == t) {
        count ++;
      }
    }
    return count;
  }

}

interface AI<T> {

  T get(T t);

}

interface MyFunc<T> {

  /**
   * 兼容按 类名.方法名
   * @param t1 调用类型
   * @param t2 传递实参
   * @return
   */
  boolean func(T t1, T t2);

}

class HighTemp {

  private int hTemp;

  public HighTemp(int hTemp) {
    this.hTemp = hTemp;
  }

  public boolean sameTemp(HighTemp highTemp) {
    return hTemp == highTemp.hTemp;
  }

  public boolean lessThanTemp(HighTemp highTemp) {
    return hTemp < highTemp.hTemp;
  }

}