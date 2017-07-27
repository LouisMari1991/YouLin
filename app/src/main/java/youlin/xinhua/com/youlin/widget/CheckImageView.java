package youlin.xinhua.com.youlin.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Checkable;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-21 16:50
 * version: 1.0
 * </pre>
 */

public class CheckImageView extends View implements Checkable {

  public CheckImageView(Context context) {
    super(context);
  }

  public CheckImageView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  public CheckImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  private boolean mChecked;

  @Override public void setChecked(boolean b) {
    mChecked = b;
    invalidate();
  }

  @Override public boolean isChecked() {
    return mChecked;
  }

  @Override public void toggle() {
    setChecked(!mChecked);
  }

  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (mChecked) {
      Drawable border = ContextCompat.getDrawable(getContext(), R.drawable.check_on);
      border.setBounds(0, 0, getWidth(), getHeight());
      border.draw(canvas);
    } else {
      Drawable border = ContextCompat.getDrawable(getContext(), R.drawable.check_off);
      border.setBounds(0, 0, getWidth(), getHeight());
      border.draw(canvas);
    }
  }
}
