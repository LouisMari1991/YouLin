package youlin.xinhua.com.youlin.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import youlin.xinhua.com.youlin.R;

/**
 * Created by YH on 2017-03-16.
 */

public class DrawingView extends View {

  private Paint  mPaint;
  private Path   mPath;
  private Bitmap mBitmap;
  private Canvas mChacheCanvas;

  private Bitmap mSignatureBitmap;

  private float preX;
  private float preY;

  private int screenWidth;
  private int screenHeight;

  private Rect mBitmapBound;

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
    Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_club_signature);

    int width = bitmap.getWidth();
    int height = bitmap.getHeight();
    float newWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 300,
        getResources().getDisplayMetrics());
    float newHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50,
        getResources().getDisplayMetrics());

    // 计算缩放比例
    float scaleWidth = (newWidth) / width;
    float scaleHeight = (newHeight) / height;

    // 取得想要缩放的matrix参数
    Matrix matrix = new Matrix();
    matrix.postScale(scaleWidth, scaleHeight);
    // 得到新的图片
    mSignatureBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
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
    mBitmapBound = new Rect();
    mBitmapBound.left = getWidth() / 2 - mSignatureBitmap.getWidth() / 2;
    mBitmapBound.right = getWidth() / 2 + mSignatureBitmap.getWidth() / 2;
    mBitmapBound.top = getHeight() / 2 - mSignatureBitmap.getHeight() / 2;
    mBitmapBound.bottom = getHeight() / 2 + mSignatureBitmap.getHeight() / 2;

    canvas.drawBitmap(mSignatureBitmap, null, mBitmapBound, mPaint);

    canvas.drawBitmap(mBitmap, 0.0f, 0.0f, mPaint);
    canvas.drawPath(mPath, mPaint);
    super.onDraw(canvas);
  }
}
