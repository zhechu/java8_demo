package demo.api;

import org.junit.Test;

import java.util.Date;
import java.util.Objects;

public class ObjectTest {

  @Test
  public void cloneTest() throws CloneNotSupportedException {
    A a = new A();
    a.cloneA();
  }

  @Test
  public void equalsAndHashCode() {
    B b1 = new B(1, "张三", new Date());
    B b2 = new B(2, "李四", new Date());
    B b3 = new B(b1.getId(), b1.getName(), b1.getCreateDate());
    System.out.println(b1.hashCode() + "," + b2.hashCode() + "," + b3.hashCode());
    // 1065154818,1067238111,1065154818
    // 758529971,2104457164,1521118594
    // 758529971,2104457164,1521118594
    System.out.println(b1.equals(b2));
    System.out.println(b2.equals(b1));
    System.out.println(b1.equals(b3));
    System.out.println(b3.equals(b2));
  }

}

class A implements Cloneable {

  public A cloneA() throws CloneNotSupportedException {
    return (A) super.clone();
  }

}

class B {

  private int id;

  private String name;

  private Date createDate;

  public B(int id, String name, Date createDate) {
    this.id = id;
    this.name = name;
    this.createDate = createDate;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    B b = (B) o;
    return id == b.id &&
        name.equals(b.name) &&
        createDate.equals(b.createDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, createDate);
  }
}