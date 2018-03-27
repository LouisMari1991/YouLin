package youlin.xinhua.com.youlin.utils;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/03/26
 *   desc   : Ctrl + Shift + T , 快速生成测试代码
 *   version: 1.0
 * </pre>
 */
@RunWith(Parameterized.class) public class DateUtilTest {

  String time = "2018-03-05 18:00:00";

  long timeStamp = 156513215320000L;

  Date date;

  @Before public void setUp() {
    System.out.println("测试开始!");
    date = new Date(timeStamp);
  }

  public DateUtilTest(String time) {
    this.time = time;
  }

  @Parameterized.Parameters public static Collection primeNumbers() {
    return Arrays.asList(new String[] { "2017-08-04", "2017-08-04 15:30:00" });
  }

  @After public void testDown() {
    System.out.println("测试结束!");
  }

  @Test public void dateToStamp() throws Exception {
    Assert.assertNotEquals("测试 [dateToStamp]", time, DateUtil.stampToDate(timeStamp));
  }

  /**
   * 传入 `"2018-03-05"` , 抛出解析异常.
   * 那我们怎么验证一个方法是否抛出了异常呢？可以给@Test注解设置expected参数来实现
   * 抛出了对应的异常则测试成功，反之则测试失败。
   */
  @Test(expected = ParseException.class) public void stampToDate() throws Exception {
    //Assert.assertNotEquals(4, DateUtil.dateToStamp(time));
    Assert.assertNotEquals(4, DateUtil.dateToStamp("2018-03-05"));
  }

  @Test public void normalTimeFormat() throws Exception {
  }

  @Test public void friendlyTimeFormat() throws Exception {
  }

  @Test public void chatTimeFormat() throws Exception {
  }

  @Test public void formatTimeDown() throws Exception {
  }

  @Test public void toDate() throws Exception {
  }

  @Test public void getChatTimeStr() throws Exception {
  }
}