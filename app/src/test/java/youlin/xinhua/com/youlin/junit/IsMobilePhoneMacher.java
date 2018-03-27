package youlin.xinhua.com.youlin.junit;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/03/26
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class IsMobilePhoneMacher extends BaseMatcher<String> {

  @Override public boolean matches(Object item) {

    if (item == null) {
      return false;
    }

    Pattern pattern = Pattern.compile("(1|861)(3|5|7|8)\\d{9}$*");
    Matcher matcher = pattern.matcher((String) item);

    return matcher.find();
  }

  /**
   * 给期待断言成功的对象增加描述
   */
  @Override public void describeTo(Description description) {
    description.appendText("预计此字符串是手机号码！");
  }

  /**
   * 给断言失败的对象增加描述
   */
  @Override public void describeMismatch(Object item, Description description) {
    description.appendText(item.toString() + "不是手机号码！");
  }
}
