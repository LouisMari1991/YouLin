package youlin.xinhua.com.youlin.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-09-18 16:36
 * version: 1.0
 * </pre>
 */

public class FilletRelativeLayout extends RelativeLayout {

  Path  mPath = new Path();
  RectF mRect = new RectF();

  int radius = -1;

  public FilletRelativeLayout(Context context) {
    this(context, null);
  }

  public FilletRelativeLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public FilletRelativeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    radius = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5,
        getResources().getDisplayMetrics());
  }

  @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    mPath.reset();
    mRect.set(0, 0, w, h);
    mPath.addRoundRect(mRect, radius, radius, Path.Direction.CW);
  }

  @Override protected void dispatchDraw(Canvas canvas) {
    canvas.save();
    canvas.clipPath(mPath);
    super.dispatchDraw(canvas);
    canvas.restore();
  }
}
