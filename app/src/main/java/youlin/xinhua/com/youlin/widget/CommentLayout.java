package youlin.xinhua.com.youlin.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import youlin.xinhua.com.im.utils.EaseSmileUtils;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-09-22 15:23
 * version: 1.0
 * </pre>
 */

public class CommentLayout extends LinearLayout {

  @BindView(R.id.et_comment)              EditText      etComment;
  @BindView(R.id.iv_collet)               CheckableView checkCollect;
  @BindView(R.id.iv_share)                ImageView     imageShare;
  @BindView(R.id.aurora_menuitem_ib_send) Button        btnSend;

  private CharSequence mInput = "";

  private OnCommentLayoutClickListener mOnCommentLayoutClickListener;

  public CommentLayout(Context context) {
    this(context, null);
  }

  public CommentLayout(Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public CommentLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    inflate(context, R.layout.layout_bottom_comment, this);
    ButterKnife.bind(this);
    init();
  }

  private void init() {
    etComment.setHint(EaseSmileUtils.getSiiledText(getContext(), "★写评论", "★", R.drawable.ic_pen));
    btnSend.setEnabled(false);
    etComment.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (s.length() >= 1 && start == 0 && before == 0) { // Starting input
          btnSend.setEnabled(true);
        } else if (s.length() == 0 && before >= 1) { // Clear content
          btnSend.setEnabled(false);
        }
      }

      @Override public void afterTextChanged(Editable s) {

      }
    });
  }

  public void changedInputType(int type) {
    switch (type) {
      case 0: {
        checkCollect.setVisibility(View.VISIBLE);
        imageShare.setVisibility(View.VISIBLE);
        btnSend.setVisibility(View.GONE);
      }
      break;
      case 1: {
        checkCollect.setVisibility(View.GONE);
        imageShare.setVisibility(View.GONE);
        btnSend.setVisibility(View.VISIBLE);
      }
      break;
      default:
        break;
    }
  }

  @OnClick({ R.id.iv_collet, R.id.iv_share, R.id.aurora_menuitem_ib_send })
  public void click(View view) {
    switch (view.getId()) {
      case R.id.iv_collet: {
        if (mOnCommentLayoutClickListener != null) {
          CheckableView checkableView = (CheckableView) view;
          mOnCommentLayoutClickListener.onCollectChecked(checkableView, checkableView.isChecked());
        }
      }
      break;
      case R.id.iv_share: {
        if (mOnCommentLayoutClickListener != null) {
          mOnCommentLayoutClickListener.onSendClick(view);
        }
      }
      break;
      case R.id.aurora_menuitem_ib_send: {
        if (mOnCommentLayoutClickListener != null) {
          mOnCommentLayoutClickListener.onSendClick(view);
        }
      }
      break;
      default:
        break;
    }
  }

  public void setOnCommentLayoutClickListener(
      OnCommentLayoutClickListener onCommentLayoutClickListener) {
    mOnCommentLayoutClickListener = onCommentLayoutClickListener;
  }

  public interface OnCommentLayoutClickListener {
    void onCollectChecked(CheckableView view, boolean isChecked);

    void onSharedClick(View view);

    void onSendClick(View view);
  }
}
