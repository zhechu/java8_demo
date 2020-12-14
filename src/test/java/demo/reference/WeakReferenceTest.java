package demo.reference;

import org.junit.Test;

import java.lang.ref.WeakReference;

public class WeakReferenceTest {

  @Test
  public void weakReference() {
    Integer integer = new Integer(22000);
    WeakReference<Integer> weakInteger = new WeakReference(integer);
    int i = 0;
    while (true) {
      if (weakInteger.get() != null) {
        i++;
        System.out.println("Object is alive for " + i + " loops - " + weakInteger);
      } else {
        System.out.println("Object has been collected.");
        break;
      }
    }
  }

  @Test
  public void clear() {
    Entry entry = null;

    {
      Integer key = new Integer(1);
      Integer value = new Integer(2);
      entry = new Entry(key, value);
      System.out.println("key:" + key + ", value:" + value);
    }

    WeakReference<Integer> weakReference = new WeakReference<>(new Integer(11));
    System.out.println("value:" + weakReference.get());

    System.gc();

    System.out.println("value:" + weakReference.get());

    System.out.println("key:" + entry.get() + ", value:" + entry.getValue());
  }

}

class Entry extends WeakReference<Integer> {

  Object value;

  Entry(Integer k, Object v) {
    super(k);
    value = v;
  }

  public Object getValue() {
    return value;
  }

}