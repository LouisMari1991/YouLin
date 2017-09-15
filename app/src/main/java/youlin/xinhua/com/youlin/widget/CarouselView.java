package youlin.xinhua.com.youlin.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.reflect.Field;
import java.util.List;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.bean.TuiJianBean;
import youlin.xinhua.com.youlin.utils.ImageLoader;
import youlin.xinhua.com.youlin.utils.MeasureUtils;
import youlin.xinhua.com.youlin.widget.animation.FixedSpeedScroller;
import youlin.xinhua.com.youlin.widget.animation.ScaleInTransformer;

/**
 * Created by Leo on 2017/7/12.
 */

public class CarouselView extends LinearLayout implements ViewPager.OnPageChangeListener {

  private List<TuiJianBean.Banner> mImageList;
  private innerViewPager           mViewPager;
  private ImageAdapter             mAdapter;
  private Handler                  mHandler;
  private BannerCircleIndicator    bannerCircleIndicator;

  private boolean mAutoScroll     = true;
  private int     mScrollInterval = 3000; //自动轮播间隔3秒

  public CarouselView(Context context) {
    this(context, null);
  }

  public CarouselView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CarouselView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    setOrientation(VERTICAL);
  }

  public void setImageList(List<TuiJianBean.Banner> imageList) {
    mImageList = imageList;
    if (mViewPager == null) {
      mViewPager = new innerViewPager(getContext());
      mViewPager.setClipToPadding(false);

      MarginLayoutParams lp =
          new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MeasureUtils.dp2px(180));

      int margin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30,
          getResources().getDisplayMetrics());

      lp.leftMargin = margin;
      lp.rightMargin = margin;

      mViewPager.setLayoutParams(lp);

      mAdapter = new ImageAdapter();

      mViewPager.setOffscreenPageLimit(3);

      int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5,
          getResources().getDisplayMetrics());

      mViewPager.setPageMargin(pageMargin);
      mViewPager.setPageTransformer(true, new ScaleInTransformer());
      setViewPagerScrollSpeed();
      mViewPager.setAdapter(mAdapter);
      this.addView(mViewPager);
      mViewPager.setCurrentItem(mImageList.size() * 1000);
      mHandler = new Handler(Looper.getMainLooper());
    } else {
      mAdapter.notifyDataSetChanged();
    }
    mViewPager.removeOnPageChangeListener(this);
    mViewPager.addOnPageChangeListener(this);
    if (bannerCircleIndicator == null) {
      bannerCircleIndicator = new BannerCircleIndicator(getContext());
      ViewGroup.LayoutParams lp =
          new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, MeasureUtils.dp2px(27));
      bannerCircleIndicator.setLayoutParams(lp);
      bannerCircleIndicator.createIndicators(imageList.size());
      bannerCircleIndicator.setCurrentPosition(0);
      addView(bannerCircleIndicator);
    } else {
      bannerCircleIndicator.createIndicators(imageList.size());
    }
    if (mAutoScroll) {
      openAutoScroll();
    }
  }

  /**
   * 设置自动轮播间隔时长
   *
   * @param interval 单位 ms
   */
  public void setScrollInterval(int interval) {
    mScrollInterval = interval;
  }

  /**
   * 开启自动轮播
   */
  public void openAutoScroll() {
    mAutoScroll = true;
    autoScroll();
  }

  /**
   * 关闭自动轮播
   */
  public void closeAutoScroll() {
    mAutoScroll = false;
    mHandler.removeCallbacks(autoScrollMission);
  }

  private void autoScroll() {
    mHandler.removeCallbacks(autoScrollMission);
    mHandler.postDelayed(autoScrollMission, mScrollInterval);
  }

  private Runnable autoScrollMission = new Runnable() {
    @Override public void run() {
      mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
      autoScroll();
    }
  };

  @Override
  public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

  }

  @Override public void onPageSelected(int position) {
    final int i = position % mImageList.size();
    bannerCircleIndicator.setCurrentPosition(i);
  }

  @Override public void onPageScrollStateChanged(int state) {

  }

  private class ImageAdapter extends PagerAdapter {

    @Override public int getCount() {
      if (mImageList != null) {
        return Integer.MAX_VALUE;
      }
      return 0;
    }

    @Override public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }

    @Override public Object instantiateItem(ViewGroup container, int position) {
      final int i = position % mImageList.size();
      final View view = View.inflate(getContext(), R.layout.itme_imageshow, null);
      final ImageView imageView = (ImageView) view.findViewById(R.id.image_show);
      TextView title = (TextView) view.findViewById(R.id.text_title);
      title.setText(mImageList.get(i).getTitle());
      ImageLoader.displayFadeImage(mImageList.get(i).getUrl(), imageView,
          R.drawable.shape_while_rectangle);
      imageView.setOnClickListener(new OnClickListener() {
        @Override public void onClick(View v) {
          //TuiJianBean.Banner banner = mImageList.get(i);
          //ArticlDetailActivity.launch(imageView.getContext(), banner.getDetailUrl(),
          //    banner.getUuid(), banner.getTitle(), banner.getDescribe(), banner.getUrl(), 1);
        }
      });
      container.addView(view);
      return view;
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((View) object);
    }
  }

  private class innerViewPager extends ViewPager {

    public innerViewPager(Context context) {
      this(context, null);
    }

    public innerViewPager(Context context, AttributeSet attrs) {
      super(context, attrs);
    }

    @Override public boolean onTouchEvent(MotionEvent ev) {
      if (!mAutoScroll) return super.onTouchEvent(ev);
      int action = ev.getAction();
      switch (action) {
        case MotionEvent.ACTION_DOWN:
          //暂时关闭自动滑动
          mHandler.removeCallbacks(autoScrollMission);
          break;
        case MotionEvent.ACTION_UP:
          //开启自动滑动
          autoScroll();
          break;
      }
      return super.onTouchEvent(ev);
    }
  }

  /**
   * 图片加载器用户自己实现
   */

  private void setViewPagerScrollSpeed() {
    try {
      Field mScroller = null;
      mScroller = ViewPager.class.getDeclaredField("mScroller");
      mScroller.setAccessible(true);
      FixedSpeedScroller scroller = new FixedSpeedScroller(mViewPager.getContext());
      mScroller.set(mViewPager, scroller);
    } catch (NoSuchFieldException e) {

    } catch (IllegalArgumentException e) {

    } catch (IllegalAccessException e) {

    }
  }
}
