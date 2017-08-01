package youlin.xinhua.com.youlin.widget;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 *     desc   : TO-DO
 *     author : 罗顺翔
 *     time   : 2017/5/5 0005 1451
 *     version: 1.0
 * </pre>
 */

public class CommonToolbar extends RelativeLayout {

  @BindView(R.id.toolbar_text_title)   TextView  mTextTitle;
  @BindView(R.id.toolbar_text_right)   TextView  mTextRight;
  @BindView(R.id.toolbar_img_back)     ImageView mImgBack;
  @BindView(R.id.toolbar_back_content) View      mView;
  @BindView(R.id.toolbar_image_right)  ImageView mImageViewRight;
  @BindView(R.id.right_container)      View      rightContainer; // 右边点击事件View

  public void isShowTitle(boolean b) {
    mTextTitle.setVisibility(b ? View.VISIBLE : View.GONE);
  }

  public void setTextTitle(CharSequence title) {
    mTextTitle.setText(title);
  }

  public void isShowImgBack(boolean b) {
    mImgBack.setVisibility(b ? View.VISIBLE : View.GONE);
  }

  public void isShowRight(boolean b) {
    mTextRight.setVisibility(b ? View.VISIBLE : View.GONE);
  }

  public void isShowImageViewRight(boolean b) {
    mImageViewRight.setVisibility(b ? View.VISIBLE : View.GONE);
  }

  public void setImageViewRight(int resourceID) {
    mImageViewRight.setImageResource(resourceID);
  }

  public void setImageViewRightOnClickListener(OnClickListener listener) {
    //mImageViewRight.setOnClickListener(listener);
    rightContainer.setOnClickListener(listener);
  }

  public void setTextRight(CharSequence title) {
    mTextRight.setText(title);
  }

  public void setTextRightOnClickListener(OnClickListener listener) {
    //mTextRight.setOnClickListener(listener);
    rightContainer.setOnClickListener(listener);
  }

  public void setImgBackOnClickListener(OnClickListener listener) {
    mView.setOnClickListener(listener);
  }

  public void setTextTitleColor(@ColorRes int colorRes) {
    mTextTitle.setTextColor(getResources().getColor(colorRes));
  }

  public void setImgBackDrawable(@DrawableRes int drawableRes) {
    mImgBack.setImageDrawable(getResources().getDrawable(drawableRes));
  }

  public CommonToolbar(@NonNull Context context) {
    super(context);
    init(context);
  }

  public CommonToolbar(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  private void init(Context context) {
    View.inflate(context, R.layout.view_toolbar, this);
    ButterKnife.bind(this);
  }
}
