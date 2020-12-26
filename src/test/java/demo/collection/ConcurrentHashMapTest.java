package demo.collection;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

  @Test
  public void putIfAbsent() throws InterruptedException {
    ConcurrentHashMap<String, List<String>> topicMap = new ConcurrentHashMap<>();

    // 主题a，关联的设备
    Thread threadA = new Thread(() -> {
      List<String> deviceList = Lists.newArrayList();
      deviceList.add("device-1");
      deviceList.add("device-2");

      try {
        Thread.sleep(100);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      List<String> deviceOriginList = topicMap.putIfAbsent("topic-a", deviceList);
      if (null != deviceOriginList) {
        deviceOriginList.addAll(deviceList);
      }

      System.out.println(JSON.toJSON(topicMap));
    });

    // 主题a，关联的设备
    Thread threadB = new Thread(() -> {
      List<String> deviceList = Lists.newArrayList();
      deviceList.add("device-11");
      deviceList.add("device-22");
      List<String> deviceOriginList = topicMap.putIfAbsent("topic-a", deviceList);
      if (null != deviceOriginList) {
        deviceOriginList.addAll(deviceList);
      }

      System.out.println(JSON.toJSON(topicMap));
    });

    // 主题b，关联的设备
    Thread threadC = new Thread(() -> {
      List<String> deviceList = Lists.newArrayList();
      deviceList.add("device-111");
      deviceList.add("device-222");
      List<String> deviceOriginList = topicMap.putIfAbsent("topic-b", deviceList);
      if (null != deviceOriginList) {
        deviceOriginList.addAll(deviceList);
      }

      System.out.println(JSON.toJSON(topicMap));
    });

    threadA.start();
    threadB.start();
    threadC.start();

    threadA.join();
    threadB.join();
    threadC.join();
  }

}
