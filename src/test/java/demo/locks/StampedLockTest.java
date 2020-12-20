package demo.locks;

import org.junit.Test;

import java.util.concurrent.locks.StampedLock;

public class StampedLockTest {

  @Test
  public void point() {

  }

}

class Point {

  private double x, y;

  private final StampedLock stampedLock = new StampedLock();

  /**
   * 写锁的使用
   * @param deltaX
   * @param deltaY
   */
  void move(double deltaX, double deltaY) {
    // 获取写锁
    long stamp = stampedLock.writeLock();
    try {
      x += deltaX;
      y += deltaY;
    } finally {
      // 释放写锁
      stampedLock.unlockWrite(stamp);
    }
  }

  /**
   * 乐观读锁的使用
   * @return
   */
  double distanceFromOrigin() {
    // 获取乐观读锁
    long stamp = stampedLock.tryOptimisticRead();
    double currentX = x;
    double currentY = y;

    // 检查乐观读锁后是否有其他写锁发生，有则返回 false
    if (!stampedLock.validate(stamp)) {
      // 获取悲观读锁
      stamp = stampedLock.readLock();

      try {
        currentX = x;
      } finally {
        // 释放悲观读锁
        stampedLock.unlockRead(stamp);
      }
    }

    return Math.sqrt(currentX * currentX + currentY * currentY);
  }

  /**
   * 悲观读锁以及读锁升级写锁的使用
   * @param newX
   * @param newY
   */
  void moveIfAtOrigin(double newX, double newY) {
    // 获取悲观读锁
    long stamp = stampedLock.readLock();
    try {
      while (x == 0.0 && y == 0.0) {
        // 读锁转换为写锁
        long ws = stampedLock.tryConvertToWriteLock(stamp);
        // 转换成功
        if (ws != 0L) {
          // 更新票据
          stamp = ws;
          x = newX;
          y = newY;
          break;
        } else {
          // 转换失败释放读锁
          stampedLock.unlockRead(stamp);
          // 强制获取写锁
          stamp = stampedLock.writeLock();
        }
      }
    } finally {
      // 释放所有锁
      stampedLock.unlock(stamp);
    }
  }

}
