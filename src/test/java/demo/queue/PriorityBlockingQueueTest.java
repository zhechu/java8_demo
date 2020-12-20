package demo.queue;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTest {

  @Test
  public void task() {
    int capacity = 100;
    PriorityBlockingQueue<Task> priorityBlockingQueue = new PriorityBlockingQueue<>(capacity);
    Random random = new Random();
    for (int i = 0; i < capacity; i++) {
      Task task = new Task();
      task.setPriority(random.nextInt(capacity));
      task.setTaskName("taskName" + i);
      priorityBlockingQueue.offer(task);
    }

    // 取出任务执行
    while (!priorityBlockingQueue.isEmpty()) {
      Task task = priorityBlockingQueue.poll();
      if (task != null) {
        task.doSomeThing();
      }
    }
  }

}

class Task implements Comparable<Task>{

  private int priority = 0;
  private String taskName;

  public void doSomeThing() {
    System.out.println(taskName + ":" + priority);
  }

  @Override
  public int compareTo(Task task) {
    if (this.priority > task.getPriority()) {
      return 1;
    } else {
      return -1;
    }
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  public String getTaskName() {
    return taskName;
  }

  public void setTaskName(String taskName) {
    this.taskName = taskName;
  }

}
