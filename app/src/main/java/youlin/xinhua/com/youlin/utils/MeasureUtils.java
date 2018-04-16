package youlin.xinhua.com.youlin.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * <pre>
 *     author : ${xuemei}
 *     e-mail : 1840494174@qq.com
 *     time   : 2017/04/19
 *     desc   : 单位转换，测量工具
 *     version: 1.0
 * </pre>
 */

public class MeasureUtils {
  private MeasureUtils() {
    throw new AssertionError();
  }

  /**
   * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
   */
  public static int dp2px(float dpValue) {
    final float scale = Resources.getSystem().getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
  }

  public static float dp2px(Context context, float dp) {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
        context.getResources().getDisplayMetrics());
  }

  public static float sp2px(Context context, float sp) {
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp,
        context.getResources().getDisplayMetrics());
  }

  public static int getMeasuredWidthWithMargins(View child) {
    final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();
    return child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
  }

  public static DisplayMetrics getDisplayMetrics(Context context) {
    if (context == null) {
      return null;
    }
    return context.getResources().getDisplayMetrics();
    //        activity.getWindowManager().getDefaultDisplay().getMetrics();
  }

  public static int[] getViewLocation(View view) {
    int[] location = new int[2];
    view.getLocationOnScreen(location);
    return location;
  }

  public static int getScreenWidth() {
    return Resources.getSystem().getDisplayMetrics().widthPixels;
  }

  public static int getScreenHeight() {
    return Resources.getSystem().getDisplayMetrics().heightPixels;
  }

  /**
   * 获得屏幕高度
   */
  public static int getScreenWidth(Context context) {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    DisplayMetrics outMetrics = new DisplayMetrics();
    wm.getDefaultDisplay().getMetrics(outMetrics);
    return outMetrics.widthPixels;
  }

  /**
   * 获得屏幕宽度
   */
  public static int getScreenHeight(Context context) {
    WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    DisplayMetrics outMetrics = new DisplayMetrics();
    wm.getDefaultDisplay().getMetrics(outMetrics);
    return outMetrics.heightPixels;
  }

  /**
   * 获得状态栏的高度
   */
  public static int getStatusHeight(Context context) {

    int statusHeight = -1;
    try {
      Class<?> clazz = Class.forName("com.android.internal.R$dimen");
      Object object = clazz.newInstance();
      int height = Integer.parseInt(clazz.getField("status_bar_height").get(object).toString());
      statusHeight = context.getResources().getDimensionPixelSize(height);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return statusHeight;
  }

  /**
   * 获取当前屏幕截图，包含状态栏
   */
  public static Bitmap snapShotWithStatusBar(Activity activity) {
    View view = activity.getWindow().getDecorView();
    view.setDrawingCacheEnabled(true);
    view.buildDrawingCache();
    Bitmap bmp = view.getDrawingCache();
    int width = getScreenWidth(activity);
    int height = getScreenHeight(activity);
    Bitmap bp = null;
    bp = Bitmap.createBitmap(bmp, 0, 0, width, height);
    view.destroyDrawingCache();
    return bp;
  }

  /**
   * 获取当前屏幕截图，不包含状态栏
   */
  public static Bitmap snapShotWithoutStatusBar(Activity activity) {
    View view = activity.getWindow().getDecorView();
    view.setDrawingCacheEnabled(true);
    view.buildDrawingCache();
    Bitmap bmp = view.getDrawingCache();
    Rect frame = new Rect();
    activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
    int statusBarHeight = frame.top;

    int width = getScreenWidth(activity);
    int height = getScreenHeight(activity);
    Bitmap bp = null;
    bp = Bitmap.createBitmap(bmp, 0, statusBarHeight, width, height - statusBarHeight);
    view.destroyDrawingCache();
    return bp;
  }

  /**
   * 设置某个View的margin
   *
   * @param view 需要设置的view
   * @param isDp 需要设置的数值是否为DP
   * @param left 左边距
   * @param right 右边距
   * @param top 上边距
   * @param bottom 下边距
   */
  public static ViewGroup.LayoutParams setViewMargin(View view, boolean isDp, int left, int right,
      int top, int bottom) {
    if (view == null) {
      return null;
    }

    int leftPx = left;
    int rightPx = right;
    int topPx = top;
    int bottomPx = bottom;
    ViewGroup.LayoutParams params = view.getLayoutParams();
    ViewGroup.MarginLayoutParams marginParams = null;
    //获取view的margin设置参数
    if (params instanceof ViewGroup.MarginLayoutParams) {
      marginParams = (ViewGroup.MarginLayoutParams) params;
    } else {
      //不存在时创建一个新的参数
      marginParams = new ViewGroup.MarginLayoutParams(params);
    }

    //根据DP与PX转换计算值
    if (isDp) {
      leftPx = dp2px(left);
      rightPx = dp2px(right);
      topPx = dp2px(top);
      bottomPx = dp2px(bottom);
    }
    //设置margin
    marginParams.setMargins(leftPx, topPx, rightPx, bottomPx);
    view.setLayoutParams(marginParams);
    view.requestLayout();
    return marginParams;
  }
}
