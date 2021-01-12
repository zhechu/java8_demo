package demo.annotation;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Person.List.class)
public @interface Person {

  String role() default "";

  @Target(ElementType.TYPE)
  @Retention(RetentionPolicy.RUNTIME)
  @interface List {

    Person[] value();

  }

}