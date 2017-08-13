package youlin.xinhua.com.youlin.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by YH on 2017-03-16.
 */

public class DrawingView extends View {

  private Paint  mPaint;
  private Path   mPath;
  private Bitmap mBitmap;
  private Canvas mChacheCanvas;

  private float preX;
  private float preY;

  private int screenWidth;
  private int screenHeight;

  private Paint mTextPaint;
  private Rect  mTextBound;

  private String mTextStr = "手写签名";

  public DrawingView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public void clearCanvas() {
    mBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
    mChacheCanvas.setBitmap(mBitmap);
    invalidate();
  }

  public Bitmap getBitmap() {
    return mBitmap;
  }

  private void init(Context context) {
    screenWidth = context.getResources().getDisplayMetrics().widthPixels;
    screenHeight = context.getResources().getDisplayMetrics().heightPixels;
    mBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);
    mChacheCanvas = new Canvas();
    mChacheCanvas.setBitmap(mBitmap);
    mPath = new Path();
    mPaint = new Paint();
    mPaint.setColor(Color.BLACK);
    mPaint.setAntiAlias(true);
    mPaint.setDither(true);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(10.0f);

    // initTextPaint
    mTextPaint = new Paint();
    mTextPaint.setColor(Color.parseColor("#edf4fa"));
    mTextPaint.setTextSize(
        TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 34, getResources().getDisplayMetrics()));
    mTextPaint.setAntiAlias(true);
    mTextPaint.setTextAlign(Paint.Align.LEFT);
    mTextBound = new Rect();
    mTextPaint.getTextBounds(mTextStr, 0, mTextStr.length(), mTextBound);
  }

  @Override public boolean onTouchEvent(MotionEvent event) {
    float x = event.getX();
    float y = event.getY();
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN: {
        mPath.moveTo(x, y);
        preX = x;
        preY = y;
        break;
      }
      case MotionEvent.ACTION_UP: {
        mChacheCanvas.drawPath(mPath, mPaint);
        mPath.reset();
        break;
      }
      case MotionEvent.ACTION_MOVE: {
        float dx = Math.abs(preX - x);
        float dy = Math.abs(preY - y);
        if (dx > 10.0f || dy > 10.0f) {
          mPath.quadTo(preX, preY, x, y);
          this.preX = x;
          this.preY = y;
          break;
        }
        break;
      }
    }
    invalidate();
    return true;
  }

  @Override protected void onDraw(Canvas canvas) {
    canvas.drawText(mTextStr, getWidth() / 2 - mTextBound.width() / 2 - mTextBound.left,
        getHeight() / 2 - mTextBound.height() / 2, mTextPaint);
    canvas.drawBitmap(mBitmap, 0.0f, 0.0f, mPaint);
    canvas.drawPath(mPath, mPaint);
    super.onDraw(canvas);
  }
}
