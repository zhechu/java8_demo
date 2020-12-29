package demo.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceTest {

  @Test
  public void compareAndSet() {
    String newReference = "b";

    AtomicStampedReference<String> reference = new AtomicStampedReference<>("a", 1);

    String expectedReference = reference.getReference();
    for (int expectedStamp = reference.getStamp(), newStamp = reference.getStamp() + 1;
        !reference.compareAndSet(expectedReference, newReference, expectedStamp,  newStamp);
        expectedReference = reference.getReference(), expectedStamp = reference.getStamp(), newStamp = expectedStamp + 1
    ) {}

    System.out.println("reference.getReference() = " + reference.getReference());
    System.out.println("reference.getStamp() = " + reference.getStamp());
  }

}
