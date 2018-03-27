package youlin.xinhua.com.youlin.base;

import android.app.Activity;
import android.os.Build;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/03/21
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class ActivityUtils {

  public static boolean activityIsAlive(Activity activity) {

    if (activity == null) {
      return false;
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      return !(activity.isDestroyed() || activity.isFinishing());
    } else {
      return !activity.isFinishing();
    }
  }

  public static Activity getSecondTopActivity() {
    return null;
  }
}
