package youlin.xinhua.com.youlin.base;

import android.app.Activity;
import android.app.ActivityOptions;
import android.os.Build;
import android.os.Handler;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import youlin.xinhua.com.youlin.utils.SPUtils;

/**
 * <pre>
 *   author : 罗顺翔
 *   e-mail : 289168296@qq.com
 *   time   : 2018/03/21
 *   desc   :
 *   version: 1.0
 * </pre>
 */
public class TranslucentHelper {

  private static final String TRANSLUCENT_STATE = "translucentState";
  private static final int INIT = 0;//表示初始
  private static final int CHANGE_STATE_FAIL = INIT + 1;//表示确认不可以切换透明状态
  private static final int CHANGE_STATE_SUCCEED = CHANGE_STATE_FAIL + 1;//表示确认可以切换透明状态
  private static int mTranslucentState = INIT;

  interface TranslucentListener {
    void onTranslucent();
  }

  private static class MyInvocationHandler implements InvocationHandler {
    private TranslucentListener listener;

    MyInvocationHandler(TranslucentListener listener) {
      this.listener = listener;
    }

    @Override public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      try {
        boolean success = (boolean) args[0];
        if (success && listener != null) {
          listener.onTranslucent();
        }
      } catch (Exception ignored) {
      }
      return null;
    }
  }

  static boolean convertActivityFromTranslucent(Activity activity) {
    if (mTranslucentState == INIT) {
      mTranslucentState = (int) SPUtils.get(TRANSLUCENT_STATE, INIT);
    }
    if (mTranslucentState == INIT) {
      convertActivityToTranslucent(activity, null);
    } else if (mTranslucentState == CHANGE_STATE_FAIL) {
      return false;
    }

    try {
      Method method = Activity.class.getDeclaredMethod("convertFromTranslucent");
      method.setAccessible(true);
      method.invoke(activity);
      mTranslucentState = CHANGE_STATE_SUCCEED;
      return true;
    } catch (Throwable t) {
      mTranslucentState = CHANGE_STATE_FAIL;
      SPUtils.put(TRANSLUCENT_STATE, CHANGE_STATE_FAIL);
      return false;
    }
  }

  static void convertActivityToTranslucent(Activity activity, final TranslucentListener listener) {
    if (mTranslucentState == CHANGE_STATE_FAIL) {
      if (listener != null) {
        listener.onTranslucent();
      }
      return;
    }

    try {
      Class<?>[] classes = Activity.class.getDeclaredClasses();
      Class<?> translucentConversionListenerClazz = null;
      for (Class clazz : classes) {
        if (clazz.getSimpleName().contains("TranslucentConversionListener")) {
          translucentConversionListenerClazz = clazz;
        }
      }

      MyInvocationHandler myInvocationHandler = new MyInvocationHandler(listener);
      Object obj = Proxy.newProxyInstance(Activity.class.getClassLoader(),
          new Class[] { translucentConversionListenerClazz }, myInvocationHandler);

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        Method getActivityOptions = Activity.class.getDeclaredMethod("getActivityOptions");
        getActivityOptions.setAccessible(true);
        Object options = getActivityOptions.invoke(activity);

        Method method = Activity.class.getDeclaredMethod("convertToTranslucent",
            translucentConversionListenerClazz, ActivityOptions.class);
        method.setAccessible(true);
        method.invoke(activity, obj, options);
      } else {
        Method method = Activity.class.getDeclaredMethod("convertToTranslucent",
            translucentConversionListenerClazz);
        method.setAccessible(true);
        method.invoke(activity, obj);
      }
      mTranslucentState = CHANGE_STATE_SUCCEED;
    } catch (Throwable t) {
      mTranslucentState = CHANGE_STATE_FAIL;
      SPUtils.put(TRANSLUCENT_STATE, CHANGE_STATE_FAIL);
      new Handler().postDelayed(new Runnable() {
        @Override public void run() {
          if (listener != null) {
            listener.onTranslucent();
          }
        }
      }, 100);
    }
  }
}
