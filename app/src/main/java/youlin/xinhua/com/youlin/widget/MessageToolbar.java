package youlin.xinhua.com.youlin.widget;

import android.content.Context;
import android.support.annotation.ColorRes;
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

public class MessageToolbar extends RelativeLayout {

  @BindView(R.id.toolbar_text_title) TextView mTextTitle;
  @BindView(R.id.toolbar_img_back) ImageView mImgBack;
  @BindView(R.id.toolbar_back_content) View mView;
  @BindView(R.id.right_container) View rightContainer; // 右边点击事件View
  @BindView(R.id.text_message) TextView text_message;

  public void setTextTitle(CharSequence title) {
    mTextTitle.setText(title);
  }

  public void setRightOnClickListener(OnClickListener listener) {
    rightContainer.setOnClickListener(listener);
  }

  public void setImgBackOnClickListener(OnClickListener listener) {
    mView.setOnClickListener(listener);
  }

  public void setTextTitleColor(@ColorRes int colorRes) {
    mTextTitle.setTextColor(getResources().getColor(colorRes));
  }

  public void setMessageCount(int msgCount) {
    if (msgCount != 0) {
      text_message.setVisibility(View.VISIBLE);
      text_message.setText(String.valueOf(msgCount));
    } else {
      text_message.setVisibility(View.INVISIBLE);
      text_message.setText("");
    }
  }

  public MessageToolbar(@NonNull Context context) {
    super(context);
    init(context);
  }

  public MessageToolbar(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  private void init(Context context) {
    View.inflate(context, R.layout.view_message_toolbar, this);
    ButterKnife.bind(this);
  }
}
