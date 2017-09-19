package youlin.xinhua.com.youlin.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-09-19 11:26
 * version: 1.0
 * </pre>
 */

public class HackyViewPager extends ViewPager {

  public HackyViewPager(Context context, AttributeSet attributeSet) {
    super(context, attributeSet);
  }

  @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
    try {
      return super.onInterceptTouchEvent(ev);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
      return false;
    }
  }
}
