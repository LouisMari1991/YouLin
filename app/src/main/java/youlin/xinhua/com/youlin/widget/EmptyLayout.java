package youlin.xinhua.com.youlin.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.utils.LogUtils;

/**
 * <pre>
 *     author : ${xuemei}
 *     e-mail : 1840494174@qq.com
 *     time   : 2017/04/19
 *     desc   : 加载、空视图
 *     version: 1.0
 * </pre>
 */

public class EmptyLayout extends FrameLayout {

  public static final int STATUS_HIDE    = 1001;
  public static final int STATUS_LOADING = 1;
  public static final int STATUS_NO_NET  = 2;
  public static final int STATUS_NO_DATA = 3;
  private OnRetryListener mOnRetryListener;
  private int mEmptyStatus = STATUS_HIDE;

  private static final int NOT_FOUND = 0;

  private int mBgColor;
  private int mNoDataDrawableId;
  private int mNoDataStr;

  @BindView(R.id.empty_container) FrameLayout mEmptyContainer; // 总的容器

  @BindView(R.id.tv_net_error)       TextView     mTvEmptyMessage; // 网络错误
  @BindView(R.id.rl_empty_container) View         mRlEmptyContainer; // 网络错误点击重试
  @BindView(R.id.empty_loading)      SpinKitView  mEmptyLoading; // LoadingView
  @BindView(R.id.no_data_layout)     LinearLayout mNoDataLayout; // 没有数据layout

  @BindView(R.id.img_nor_data) ImageView mImgNoData;
  @BindView(R.id.text_no_data) TextView  mTextNoData;

  public EmptyLayout(Context context) {
    this(context, null);
  }

  public EmptyLayout(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public EmptyLayout(@NonNull Context context, @Nullable AttributeSet attrs,
      @AttrRes int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context, attrs);
  }

  /**
   * 初始化
   */
  private void init(Context context, AttributeSet attrs) {
    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.EmptyLayout);
    try {
      mBgColor = a.getColor(R.styleable.EmptyLayout_background_color, NOT_FOUND);
      mNoDataDrawableId = a.getResourceId(R.styleable.EmptyLayout_no_data_drawable, NOT_FOUND);
      mNoDataStr = a.getResourceId(R.styleable.EmptyLayout_no_data_str, NOT_FOUND);
    } finally {
      a.recycle();
    }
    inflate(context, R.layout.view_loading_empty_layout, this);
    ButterKnife.bind(this);

    if (mBgColor != NOT_FOUND) {
      mEmptyContainer.setBackgroundColor(mBgColor);
    }
    if (mNoDataDrawableId != NOT_FOUND) {
      mImgNoData.setImageResource(mNoDataDrawableId);
    }
    if (mNoDataStr != NOT_FOUND) {
      mTextNoData.setText(mNoDataStr);
    }
    switchEmptyView();
  }

  /**
   * 隐藏视图
   */
  public void hide() {
    mEmptyStatus = STATUS_HIDE;
    switchEmptyView();
  }

  /**
   * 设置状态
   */
  public void setEmptyStatus(@EmptyStatus int emptyStatus) {
    mEmptyStatus = emptyStatus;
    LogUtils.i("[setEmptyStatus] emptyStatus : " + mEmptyStatus);
    switchEmptyView();
  }

  /**
   * 获取状态
   *
   * @return 状态
   */
  public int getEmptyStatus() {
    return mEmptyStatus;
  }

  /**
   * 设置异常消息
   *
   * @param msg 显示消息
   */
  public void setEmptyMessage(String msg) {
    mTvEmptyMessage.setText(msg);
  }

  public void hideErrorIcon() {
    mTvEmptyMessage.setCompoundDrawables(null, null, null, null);
  }

  //    /**
  //     * 设置图标
  //     * @param resId 资源ID
  //     */
  //    public void setEmptyIcon(int resId) {
  //        mIvEmptyIcon.setImageResource(resId);
  //    }
  //
  //    /**
  //     * 设置图标
  //     * @param drawable drawable
  //     */
  //    public void setEmptyIcon(Drawable drawable) {
  //        mIvEmptyIcon.setImageDrawable(drawable);
  //    }

  public void setLoadingIcon(Sprite d) {
    mEmptyLoading.setIndeterminateDrawable(d);
  }

  /**
   * 切换视图
   */
  private void switchEmptyView() {
    switch (mEmptyStatus) {
      case STATUS_LOADING:
        setVisibility(VISIBLE);
        mRlEmptyContainer.setVisibility(GONE);
        mEmptyLoading.setVisibility(VISIBLE);
        mNoDataLayout.setVisibility(GONE);
        break;
      case STATUS_NO_DATA:
        setVisibility(VISIBLE);
        mRlEmptyContainer.setVisibility(GONE);
        mEmptyLoading.setVisibility(GONE);
        mNoDataLayout.setVisibility(VISIBLE);
        break;
      case STATUS_NO_NET:
        setVisibility(VISIBLE);
        mEmptyLoading.setVisibility(GONE);
        mRlEmptyContainer.setVisibility(VISIBLE);
        mNoDataLayout.setVisibility(GONE);
        break;
      case STATUS_HIDE:
        setVisibility(GONE);
        break;
    }
  }

  /**
   * 设置重试监听器
   *
   * @param retryListener 监听器
   */
  public void setRetryListener(OnRetryListener retryListener) {
    this.mOnRetryListener = retryListener;
  }

  @OnClick(R.id.tv_net_error) public void onClick() {
    if (mOnRetryListener != null) {
      mOnRetryListener.onRetry();
    }
  }

  /**
   * 点击重试监听器
   */
  public interface OnRetryListener {
    void onRetry();
  }

  @Retention(RetentionPolicy.SOURCE) @IntDef({ STATUS_LOADING, STATUS_NO_NET, STATUS_NO_DATA })
  public @interface EmptyStatus {
  }
}