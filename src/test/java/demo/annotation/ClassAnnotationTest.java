package demo.annotation;

import org.junit.Test;

public class ClassAnnotationTest {

  @Test
  public void getAnnotationsByType() {
    Person[] persons = Man.class.getAnnotationsByType(Person.class);
    for (Person person : persons) {
      System.out.println(person);
    }
  }

}



