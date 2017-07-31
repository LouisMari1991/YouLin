package youlin.xinhua.com.youlin.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.shapes.RoundRectShape;
import android.graphics.drawable.shapes.Shape;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;
import java.util.Arrays;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-31 10:26
 * version: 1.0
 * </pre>
 */

public class ShapeImageView extends ImageView {

  public ShapeImageView(Context context) {
    this(context, null);
  }

  public ShapeImageView(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  private Paint mPaint;
  private Shape mShape;

  private float mRadius;

  public ShapeImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    setLayerType(LAYER_TYPE_HARDWARE, null);

    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MessageList, defStyleAttr, 0);
    try {
      mRadius = a.getDimension(R.styleable.MessageList_videomessage_radius, 0);
    } finally {
      a.recycle();
    }
    mPaint = new Paint();
    mPaint.setAntiAlias(true);
    mPaint.setFilterBitmap(true);
    mPaint.setColor(Color.BLACK);
    mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
  }

  @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    if (mShape == null) {
      float[] radius = new float[8];
      Arrays.fill(radius, mRadius);
      mShape = new RoundRectShape(radius, null, null);
    }
    mShape.resize(getWidth(), getHeight());
  }

  @Override protected void onDraw(Canvas canvas) {
    int saveCount = canvas.getSaveCount();
    canvas.save();
    super.onDraw(canvas);
    if (mShape != null) {
      mShape.draw(canvas, mPaint);
    }
    canvas.restoreToCount(saveCount);
  }
}
