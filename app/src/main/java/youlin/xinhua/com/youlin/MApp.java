package youlin.xinhua.com.youlin;

import android.app.Application;
import youlin.xinhua.com.youlin.im.IMPlatform;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-13 16:23
 * version: 1.0
 * </pre>
 */

public class MApp extends Application {

  private static Application context;

  @Override public void onCreate() {
    super.onCreate();
    context = this;
    IMPlatform.get().init(this);
  }

  public static Application get() {
    return context;
  }

}
