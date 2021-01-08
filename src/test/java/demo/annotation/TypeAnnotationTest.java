package demo.annotation;

import org.junit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

public class TypeAnnotationTest {

  @Test
  public void nonNull() {
    List<@NonNull String> list = new ArrayList<>();
    list.add("1");
  }

}

@Target({ElementType.TYPE_PARAMETER, ElementType.TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
@interface NonNull { }

