package youlin.xinhua.com.youlin.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     desc   : TO-DO
 *     author : 罗顺翔
 *     time   : 2017-05-26 14:49
 *     version: 1.0
 * </pre>
 */

public class AppUtils {

  public static boolean isMainProcess(Context context) {
    String packageName = context.getPackageName();
    String processName = getCurProcessName(context);
    return packageName.equalsIgnoreCase(processName);
  }

  public static String getCurProcessName(Context context) {
    String strRet = null;

    try {
      Class pid = Class.forName("android.ddm.DdmHandleAppName");
      Method activityManager = pid.getDeclaredMethod("getAppName", new Class[0]);
      strRet = (String) activityManager.invoke(pid, new Object[0]);
    } catch (Exception var7) {
      Log.w("Freeline.AppUtils", var7);
    }

    if (TextUtils.isEmpty(strRet)) {
      int pid1 = Process.myPid();
      ActivityManager activityManager1 = (ActivityManager) context.getSystemService("activity");
      List runningAppProcesses = activityManager1.getRunningAppProcesses();
      Iterator var5 = runningAppProcesses.iterator();

      while (var5.hasNext()) {
        ActivityManager.RunningAppProcessInfo appProcess =
            (ActivityManager.RunningAppProcessInfo) var5.next();
        if (appProcess.pid == pid1) {
          strRet = appProcess.processName;
          break;
        }
      }
    }

    return strRet;
  }

  /**
   * 获取包信息
   */
  public static PackageInfo getPackageInfo(Context context) {
    PackageManager pm = context.getPackageManager();
    try {
      return pm.getPackageInfo(context.getPackageName(), 0);
    } catch (PackageManager.NameNotFoundException e) {
      LogUtils.e(e.getLocalizedMessage());
    }
    return new PackageInfo();
  }


  public static String getImeiInfo(Context ctx) {
    String imei = null;
    try {
      TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
      imei = tm.getDeviceId();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return imei;
  }

  public static String getImsi(Context ctx) {
    String[] simInfo = { "", "" };
    try {
      TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
      simInfo[0] = tm.getSubscriberId();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return simInfo[0];
  }
}


