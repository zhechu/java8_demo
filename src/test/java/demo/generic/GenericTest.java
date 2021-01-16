package demo.generic;

import org.junit.Test;

public class GenericTest {

  @Test
  public void base() {
    Integer[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    Stats<Integer> stats = new Stats<>(nums);
    System.out.println(stats.average());

    Double[] doubleNums = {1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0, 10.0};
    Stats<Double> doubleStats = new Stats<>(doubleNums);
    System.out.println(doubleStats.average());

    System.out.println(stats.sameAverage(doubleStats));
  }

  @Test
  public void wildcard() {
    TwoD[] twoDArray = {new TwoD(1, 2), new TwoD(1, 21)};
    Coords<TwoD> coords = new Coords<>(twoDArray);
    Coords.showXY(coords);

    ThreeD[] threeDArray = {new ThreeD(1, 2, 3), new ThreeD(1, 21, 3)};
    Coords<ThreeD> threeCoords = new Coords<>(threeDArray);
    Coords.showXYZ(threeCoords);
  }

  @Test
  public void method() {
    Integer[] nums = {1, 2, 3, 4, 5};

    if (isIn(2, nums)) {
      System.out.println("2 is in nums");
    }

    if (!isIn(7, nums)) {
      System.out.println("7 is not in nums");
    }

    String[] strs = {"one", "two", "three", "four", "five"};

    if (isIn("two", strs)) {
      System.out.println("two is in nums");
    }

    if (!isIn("seven", strs)) {
      System.out.println("seven is not in nums");
    }

    TwoD twoD = new TwoD(1, 2);
    ThreeD threeD = new ThreeD(1, 2, 3);
    TwoD[] twoDArray = {twoD, new TwoD(1, 21), threeD};
    if (isIn(threeD, twoDArray)) {
      System.out.println("ThreeD(1, 2, 3) is in nums");
    }
  }

  static <T extends Comparable<T>, V extends T> boolean isIn(T t, V[] vs) {
    for (int i = 0; i < vs.length; i++) {
      if (t.equals(vs[i])) {
        return true;
      }
    }

    return false;
  }

}

class Stats<T extends Number> {

  private T[] nums;

  public Stats(T[] nums) {
    this.nums = nums;
  }

  public double average() {
    double sum = 0.0;
    int len = nums.length;
    for (int i = 0; i < len; i++) {
      sum += nums[i].doubleValue();
    }
    return sum / len;
  }

  public boolean sameAverage(Stats<?> stats) {
    return this.average() == stats.average();
  }

}

class TwoD implements Comparable<TwoD> {

  int x, y;

  public TwoD(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int compareTo(TwoD o) {
    return this.x - o.x;
  }

}

class ThreeD extends TwoD {

  int z;

  public ThreeD(int x, int y, int z) {
    super(x, y);
    this.z = z;
  }

}

class FourD extends ThreeD {

  int t;

  public FourD(int x, int y, int z, int t) {
    super(x, y, z);
    this.t = t;
  }

}

class Coords<T extends TwoD> {

  T[] coords;

  public Coords(T[] coords) {
    this.coords = coords;
  }

  static void showXY(Coords<?> coords) {
    for (int i = 0; i < coords.coords.length; i++) {
      System.out.println(coords.coords[i].x + "," + coords.coords[i].y);
    }
  }

  static void showXYZ(Coords<? extends ThreeD> coords) {
    for (int i = 0; i < coords.coords.length; i++) {
      System.out.println(coords.coords[i].x + "," + coords.coords[i].y + "," + coords.coords[i].z);
    }
  }

}