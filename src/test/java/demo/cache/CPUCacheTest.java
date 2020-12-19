package demo.cache;

import org.junit.Test;
import sun.misc.Contended;

/**
 * CPU 缓存
 */
public class CPUCacheTest {

  private static final int LINE_NUM = 10240;
  private static final int COLUM_NUM = 10240;

  /**
   * 二维数组先按行遍历，充分利用CPU缓存行（一般每行大小为64字节）
   */
  @Test
  public void foreachByLine() {
    long[][] array = new long[LINE_NUM][COLUM_NUM];

    long startTime = System.currentTimeMillis();
    for (int i = 0; i < LINE_NUM; i++) {
      for (int j = 0; j < COLUM_NUM; j++) {
        array[i][j] = i * 2 + j;
      }
    }
    long endTime = System.currentTimeMillis();
    long cacheTime = endTime - startTime;
    System.out.println("cacheTime:" + cacheTime);
  }

  /**
   * 二维数组先按列遍历，未充分利用CPU缓存行（一般每行大小为64字节）
   */
  @Test
  public void foreachByColum() {
    long[][] array = new long[LINE_NUM][COLUM_NUM];

    long startTime = System.currentTimeMillis();
    for (int i = 0; i < COLUM_NUM; i++) {
      for (int j = 0; j < LINE_NUM; j++) {
        array[j][i] = i * 2 + j;
      }
    }
    long endTime = System.currentTimeMillis();
    long noCacheTime = endTime - startTime;
    System.out.println("noCacheTime:" + noCacheTime);
  }

}