package youlin.xinhua.com.youlin.junit;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/03/26
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class AssertThatTest {

  @Rule public MyRule rule = new MyRule();



  @Test public void testMobilePhone() {
    Assert.assertThat("18664569168", new IsMobilePhoneMacher());
  }
}
