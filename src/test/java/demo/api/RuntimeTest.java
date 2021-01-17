package demo.api;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class RuntimeTest {

  @Test
  public void freeMemory() {
    System.out.println("free:" + Runtime.getRuntime().freeMemory());
    System.out.println("max:" + Runtime.getRuntime().maxMemory());
    System.out.println("total:" + Runtime.getRuntime().totalMemory());
  }

  @Test
  public void getProperty() {
    Map<String, String> map = new HashMap<>();

    // 运行时环境版本
    map.put("java.version", System.getProperty("java.version"));
    // 运行时环境供应商
    map.put("java.vendor", System.getProperty("java.vendor"));
    // 供应商的 URL
    map.put("java.vendor.url", System.getProperty("java.vendor.url"));
    // 安装目录
    map.put("java.home", System.getProperty("java.home"));
    // 虚拟机规范版本
    map.put("java.vm.specification.version", System.getProperty("java.vm.specification.version"));
    // 虚拟机规范供应商
    map.put("java.vm.specification.vendor", System.getProperty("java.vm.specification.vendor"));
    // 虚拟机规范名称
    map.put("java.vm.specification.name", System.getProperty("java.vm.specification.name"));
    // 虚拟机实现版本
    map.put("java.vm.version", System.getProperty("java.vm.version"));
    // 虚拟机实现供应商
    map.put("java.vm.vendor", System.getProperty("java.vm.vendor"));
    // 虚拟机实现名称
    map.put("java.vm.name", System.getProperty("java.vm.name"));
    // 运行时环境规范版本
    map.put("java.specification.version", System.getProperty("java.specification.version"));
    // 运行时环境规范供应商
    map.put("java.specification.vendor", System.getProperty("java.specification.vendor"));
    // 运行时环境规范名称
    map.put("java.specification.name", System.getProperty("java.specification.name"));
    // 类格式版本号
    map.put("java.class.version", System.getProperty("java.class.version"));
    // 类路径
    map.put("java.class.path", System.getProperty("java.class.path"));
    // 加载库时搜索的路径列表
    map.put("java.library.path", System.getProperty("java.library.path"));
    // 默认的临时文件路径
    map.put("java.io.tmpdir", System.getProperty("java.io.tmpdir"));
    // 要使用的 JIT 编译器的名称
    map.put("java.compiler", System.getProperty("java.compiler"));
    // 一个或多个扩展目录的路径
    map.put("java.ext.dirs", System.getProperty("java.ext.dirs"));
    // 操作系统的名称
    map.put("os.name", System.getProperty("os.name"));
    // 操作系统的架构
    map.put("os.arch", System.getProperty("os.arch"));
    // 操作系统的版本
    map.put("os.version", System.getProperty("os.version"));
    // 文件分隔符（在 UNIX 系统中是“/”）
    map.put("file.separator", System.getProperty("file.separator"));
    // 路径分隔符（在 UNIX 系统中是“:”）
    map.put("path.separator", System.getProperty("path.separator"));
    // 行分隔符（在 UNIX 系统中是“/n”）
    map.put("line.separator", System.getProperty("line.separator"));
    // 用户的账户名称
    map.put("user.name", System.getProperty("user.name"));
    // 用户的主目录
    map.put("user.home", System.getProperty("user.home"));
    // 用户的当前工作目录
    map.put("user.dir", System.getProperty("user.dir"));

    map.forEach((k, v) -> System.out.println(k + ":" + v));
  }

}
