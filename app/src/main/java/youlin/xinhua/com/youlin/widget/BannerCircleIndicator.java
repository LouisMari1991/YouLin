package youlin.xinhua.com.youlin.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.utils.MeasureUtils;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-09-15 11:13
 * version: 1.0
 * </pre>
 */

public class BannerCircleIndicator extends LinearLayout {

  private final static int DEFAULT_INDICATOR_WIDTH  = 7;
  private final static int DEFAULT_INDICATOR_MARGIN = 3;

  private int mIndicatorMargin = -1;
  private int mIndicatorWidth  = -1;
  private int mIndicatorHeight = -1;

  private int mIndicatorBackgroundResId           = R.drawable.shape_banner_circleindicator_check;
  private int mIndicatorUnselectedBackgroundResId = R.drawable.shape_banner_circleindicator_uncheck;
  private int mCurrentPosition                    = 0;

  public BannerCircleIndicator(Context context) {
    this(context, null);
  }

  public BannerCircleIndicator(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public BannerCircleIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    setOrientation(LinearLayout.HORIZONTAL);
    setGravity(Gravity.CENTER);
    mIndicatorMargin = MeasureUtils.dp2px(DEFAULT_INDICATOR_MARGIN);
    mIndicatorWidth = MeasureUtils.dp2px(DEFAULT_INDICATOR_WIDTH);
    mIndicatorHeight = MeasureUtils.dp2px(DEFAULT_INDICATOR_WIDTH);
  }

  public void createIndicators(int count) {
    removeAllViews();
    for (int i = 0; i < count; i++) {
      View Indicator = new View(getContext());
      Indicator.setBackgroundResource(mIndicatorUnselectedBackgroundResId);
      addView(Indicator, mIndicatorWidth, mIndicatorHeight);
      LayoutParams lp = (LayoutParams) Indicator.getLayoutParams();
      lp.leftMargin = mIndicatorMargin;
      lp.rightMargin = mIndicatorMargin;
      Indicator.setLayoutParams(lp);
    }
  }

  public void setCurrentPosition(int position) {

    View currentIndicator = getChildAt(mCurrentPosition);
    LayoutParams lp1 = (LayoutParams) currentIndicator.getLayoutParams();
    lp1.width = MeasureUtils.dp2px(DEFAULT_INDICATOR_WIDTH);
    lp1.height = MeasureUtils.dp2px(DEFAULT_INDICATOR_WIDTH);
    currentIndicator.setLayoutParams(lp1);
    currentIndicator.setBackgroundResource(mIndicatorUnselectedBackgroundResId);

    View selectedIndicator = getChildAt(position);
    LayoutParams lp2 = (LayoutParams) selectedIndicator.getLayoutParams();
    lp2.width = MeasureUtils.dp2px(14);
    lp2.height = MeasureUtils.dp2px(7);
    selectedIndicator.setLayoutParams(lp2);
    selectedIndicator.setBackgroundResource(mIndicatorBackgroundResId);

    mCurrentPosition = position;
  }
}
