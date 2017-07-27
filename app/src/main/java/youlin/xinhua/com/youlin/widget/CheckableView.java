package youlin.xinhua.com.youlin.widget;

import android.content.Context;
import android.content.res.TypedArray;
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
 * desc   : 自适应图片大小的简略版本Checkbox
 * author : 罗顺翔
 * time   : 2017-07-21 17:10
 * version: 1.0
 * </pre>
 */

public class CheckableView extends View implements Checkable {

  private boolean mChecked;

  private static final int NOT_FOUND = 0;

  private int normalDrawableId;
  private int checkedDrawableId;

  public CheckableView(Context context) {
    super(context);
  }

  public CheckableView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CheckableView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    TypedArray a =
        context.obtainStyledAttributes(attrs, R.styleable.CheckableView, defStyleAttr, 0);
    try {
      normalDrawableId = a.getResourceId(R.styleable.CheckableView_normalDrawable, NOT_FOUND);
      checkedDrawableId = a.getResourceId(R.styleable.CheckableView_checkedDrawable, NOT_FOUND);
    } finally {
      a.recycle();
    }
  }

  @Override public void setChecked(boolean b) {
    if (mChecked == b) {
      return;
    }
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
      if (checkedDrawableId != NOT_FOUND) {
        Drawable border = ContextCompat.getDrawable(getContext(), checkedDrawableId);
        border.setBounds(0, 0, getWidth(), getHeight());
        border.draw(canvas);
      }
    } else {
      if (normalDrawableId != NOT_FOUND) {
        Drawable border = ContextCompat.getDrawable(getContext(), normalDrawableId);
        border.setBounds(0, 0, getWidth(), getHeight());
        border.draw(canvas);
      }
    }
  }
}
