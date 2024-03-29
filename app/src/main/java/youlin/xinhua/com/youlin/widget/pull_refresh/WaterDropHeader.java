package youlin.xinhua.com.youlin.widget.pull_refresh;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.internal.ProgressDrawable;
import com.scwang.smartrefresh.layout.util.DensityUtil;
import youlin.xinhua.com.youlin.widget.pull_refresh.internal.MaterialProgressDrawable;

import static android.view.View.MeasureSpec.EXACTLY;
import static android.view.View.MeasureSpec.makeMeasureSpec;

public class WaterDropHeader extends ViewGroup implements RefreshHeader {

  //<editor-fold desc="Field">
  private static final float MAX_PROGRESS_ANGLE = 0.8f;

  private RefreshState mState;
  private ImageView mImageView;
  //private WaterDropView mWaterDropView;
  private ProgressDrawable mProgressDrawable;
  private MaterialProgressDrawable mProgress;
  private int mProgressDegree = 0;
  //</editor-fold>

  private int measureHeight;

  //<editor-fold desc="ViewGroup">
  public WaterDropHeader(Context context) {
    super(context);
    this.initView(context);
  }

  public WaterDropHeader(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.initView(context);
  }

  public WaterDropHeader(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.initView(context);
  }

  @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
  public WaterDropHeader(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
    this.initView(context);
  }

  private void initView(Context context) {
    DensityUtil density = new DensityUtil();
    //mWaterDropView = new WaterDropView(context);
    //addView(mWaterDropView, MATCH_PARENT, MATCH_PARENT);
    //mWaterDropView.updateComleteState(0);

    measureHeight = DensityUtil.dp2px(44);

    mProgressDrawable = new ProgressDrawable();
    mProgressDrawable.setBounds(0, 0, density.dip2px(20), density.dip2px(20));

    mImageView = new ImageView(context);
    mProgress = new MaterialProgressDrawable(context, mImageView);
    mProgress.setBackgroundColor(0xffffffff);
    mProgress.setAlpha(255);
    mProgress.setColorSchemeColors(0xffffffff, 0xff0099cc, 0xffff4444, 0xff669900, 0xffaa66cc,
        0xffff8800);
    mImageView.setImageDrawable(mProgress);
    addView(mImageView, density.dip2px(30), density.dip2px(30));
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    LayoutParams lpImage = mImageView.getLayoutParams();
    mImageView.measure(makeMeasureSpec(lpImage.width, EXACTLY),
        makeMeasureSpec(lpImage.height, EXACTLY));
    //mWaterDropView.measure(
    //        makeMeasureSpec(getSize(widthMeasureSpec), AT_MOST),
    //        heightMeasureSpec
    //);

    int maxWidth = mImageView.getMeasuredWidth();
    int maxHeight = mImageView.getMeasuredHeight();
    setMeasuredDimension(resolveSize(maxWidth, widthMeasureSpec), measureHeight);
  }

  @Override protected void onLayout(boolean changed, int l, int t, int r, int b) {
    final int measuredWidth = getMeasuredWidth();
    final int measureHeight = getMeasuredHeight();

    //final int widthWaterDrop = mWaterDropView.getMeasuredWidth();
    //final int heightWaterDrop = mWaterDropView.getMeasuredHeight();
    //final int leftWaterDrop = measuredWidth / 2 - widthWaterDrop / 2;
    //final int topWaterDrop = 0;
    //mWaterDropView.item_rv(leftWaterDrop, topWaterDrop, leftWaterDrop + widthWaterDrop,
    //    topWaterDrop + heightWaterDrop);
    //
    //final int widthImage = mImageView.getMeasuredWidth();
    //final int heightImage = mImageView.getMeasuredHeight();
    //final int leftImage = measuredWidth / 2 - widthImage / 2;
    //int topImage = widthWaterDrop / 2 - widthImage / 2;
    //if (topImage + heightImage > mWaterDropView.getBottom() - (widthWaterDrop - widthImage) / 2) {
    //  topImage = mWaterDropView.getBottom() - (widthWaterDrop - widthImage) / 2 - heightImage;
    //}

    Rect bounds = mProgressDrawable.getBounds();

    //final int widthImage = mImageView.getMeasuredWidth();
    //final int heightImage = mImageView.getMeasuredHeight();

    final int widthImage = bounds.width();
    final int heightImage = bounds.height();

    final int leftImage = measuredWidth / 2 - widthImage / 2;
    final int topImage = measureHeight / 2 - heightImage / 2;

    mImageView.layout(leftImage, topImage, leftImage + widthImage, topImage + heightImage);
  }
  //</editor-fold>

  //<editor-fold desc="Draw">
  @Override protected void dispatchDraw(Canvas canvas) {
    super.dispatchDraw(canvas);
    canvas.save();
    canvas.translate(getWidth() / 2, getHeight() / 2);
    canvas.rotate(mProgressDegree, mProgressDrawable.width() / 2, mProgressDrawable.height() / 2);
    mProgressDrawable.draw(canvas);
    canvas.restore();
  }
  //</editor-fold>

  //<editor-fold desc="RefreshHeader">
  @Override public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {

  }

  @Override public boolean isSupportHorizontalDrag() {
    return false;
  }

  @Override public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
  }

  @Override public void onPullingDown(float percent, int offset, int headHeight, int extendHeight) {
    //mWaterDropView.updateComleteState((offset), headHeight + extendHeight);
    //mWaterDropView.postInvalidate();

    float originalDragPercent = 1f * offset / headHeight;

    float dragPercent = Math.min(1f, Math.abs(originalDragPercent));
    float adjustedPercent = (float) Math.max(dragPercent - .4, 0) * 5 / 3;
    float extraOS = Math.abs(offset) - headHeight;
    float tensionSlingshotPercent =
        Math.max(0, Math.min(extraOS, (float) headHeight * 2) / (float) headHeight);
    float tensionPercent =
        (float) ((tensionSlingshotPercent / 4) - Math.pow((tensionSlingshotPercent / 4), 2)) * 2f;
    float strokeStart = adjustedPercent * .8f;
    float rotation = (-0.25f + .4f * adjustedPercent + tensionPercent * 2) * .5f;
    mProgress.showArrow(true);
    mProgress.setStartEndTrim(0f, Math.min(MAX_PROGRESS_ANGLE, strokeStart));
    mProgress.setArrowScale(Math.min(1f, adjustedPercent));
    mProgress.setProgressRotation(rotation);
  }

  @Override public void onReleasing(float percent, int offset, int headHeight, int extendHeight) {
    //if (mState != RefreshState.Refreshing && mState != RefreshState.RefreshReleased) {
    //    mWaterDropView.updateComleteState(Math.max(offset, 0), headHeight + extendHeight);
    //    mWaterDropView.postInvalidate();
    //}
  }

  boolean isRemote = false;

  @Override public void onStateChanged(final RefreshLayout refreshLayout, RefreshState oldState,
      RefreshState newState) {
    mState = newState;

    Log.i("WaterDropHeader", "onStateChanged ,  mState : " + mState);

    if (!isRemote) {
      isRemote = true;
      refreshLayout.getLayout().postDelayed(new Runnable() {
        @Override public void run() {
          mProgressDegree = (mProgressDegree + 30) % 360;
          invalidate();
          if (mState == RefreshState.Loading
              || mState == RefreshState.PullDownToRefresh
              || mState == RefreshState.Refreshing
              || mState == RefreshState.ReleaseToRefresh
              || mState == RefreshState.Refreshing) {
            Log.i("WaterDropHeader", " run ,  mState : " + mState);
            refreshLayout.getLayout().postDelayed(this, 100);
          }
        }
      }, 100);
    }

    if (mState == RefreshState.None) {
      isRemote = false;
    }

    //switch (newState) {
    //    case None:
    //        mWaterDropView.setVisibility(View.GONE);
    //        break;
    //    case PullDownToRefresh:
    //        mWaterDropView.setVisibility(View.GONE);
    //        break;
    //    case PullDownCanceled:
    //        break;
    //    case ReleaseToRefresh:
    //        mWaterDropView.setVisibility(View.GONE);
    //        break;
    //    case Refreshing:
    //        break;
    //    case RefreshFinish:
    //        mWaterDropView.setVisibility(View.GONE);
    //        break;
    //}
  }

  //@Override
  public void onRefreshReleased(final RefreshLayout layout, int headerHeight, int extendHeight) {
    //Animator animator = mWaterDropView.createAnimator();
    //animator.addListener(new AnimatorListenerAdapter() {
    //    @Override
    //    public void onAnimationEnd(Animator animation) {
    //        mWaterDropView.animate().alpha(0).setListener(new AnimatorListenerAdapter() {
    //            public void onAnimationEnd(Animator animation) {
    //                mWaterDropView.setVisibility(GONE);
    //                mWaterDropView.setAlpha(1);
    //            }
    //        });
    //    }
    //});
    //animator.start();//开始回弹
    //item_rv.getLayout().postDelayed(new Runnable() {
    //  @Override public void run() {
    //    mProgressDegree = (mProgressDegree + 30) % 360;
    //    invalidate();
    //    if (mState == RefreshState.Refreshing || mState == RefreshState.RefreshReleased) {
    //      item_rv.getLayout().postDelayed(this, 100);
    //    }
    //  }
    //}, 100);
  }

  @Override public void onStartAnimator(RefreshLayout layout, int headHeight, int extendHeight) {

  }

  @Override public int onFinish(RefreshLayout layout, boolean success) {
    return 0;
  }

  @NonNull @Override public View getView() {
    return this;
  }

  @Override public SpinnerStyle getSpinnerStyle() {
    return SpinnerStyle.Scale;
  }

  @Override @Deprecated public void setPrimaryColors(@ColorInt int... colors) {
    //if (colors.length > 0) {
    //    mWaterDropView.setIndicatorColor(colors[0]);
    //}
  }
  //</editor-fold>
}