package youlin.xinhua.com.youlin.widget.animation;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

/**
 * <pre>
 *     author : 郭威
 *     e-mail : 18200533451@163.com
 *     time   : 2017/7/14
 *     desc   :
 *     version:
 * </pre>
 */

public class FixedSpeedScroller extends Scroller {
  private int mDuration = 1000;

  public FixedSpeedScroller(Context context) {
    super(context);
  }

  public FixedSpeedScroller(Context context, Interpolator interpolator) {
    super(context, interpolator);
  }

  public FixedSpeedScroller(Context context, Interpolator interpolator, boolean flywheel) {
    super(context, interpolator, flywheel);
  }


  @Override
  public void startScroll(int startX, int startY, int dx, int dy, int duration) {
    super.startScroll(startX, startY, dx, dy, mDuration);
  }

  @Override
  public void startScroll(int startX, int startY, int dx, int dy) {
    super.startScroll(startX, startY, dx, dy, mDuration);
  }
}
