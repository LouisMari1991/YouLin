package youlin.xinhua.com.youlin.junit;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import youlin.xinhua.com.youlin.utils.DateUtil;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/03/26
 *   desc   :
 *   version: 1.0
 * </pre>
 */
// 指定该测试类使用某个运行器
@RunWith(Parameterized.class) public class DateFormatTest {

  private String time;

  public DateFormatTest(String time) {
    this.time = time;
  }

  @Parameterized.Parameters public static Collection primeNumbers() {
    return Arrays.asList(new String[] { "2017-08-04", "2017-08-04 15:30:00" });
  }

  @Test(expected = ParseException.class) public void test() throws Exception {
    DateUtil.dateToStamp(time);
  }
}
