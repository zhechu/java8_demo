package demo.api;

import org.junit.Test;

public class PackageTest {

  @Test
  public void getPackages() {
    Package[] packages = Package.getPackages();
    for (Package p : packages) {
      System.out.println(p);
    }
  }

}
