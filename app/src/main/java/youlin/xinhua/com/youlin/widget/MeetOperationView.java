package youlin.xinhua.com.youlin.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import youlin.xinhua.com.youlin.R;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-08-23 17:15
 * version: 1.0
 * </pre>
 */

public class MeetOperationView extends FrameLayout {

  private static final int TYPE_SIGN        = 0;
  private static final int TYPE_CONTENT     = 1;
  private static final int TYPE_NORMAL_VOTE = 2;

  LinearLayout llContainer; // 总的layout
  LinearLayout llVoteContainer; // 普通投票layout

  Button   btnSign;// 签到
  TextView textTitle;// 头部文字
  TextView textContent; // 倒计时 , 显示选举换届总票数 , 签到人数
  TextView textAgreeCount;// 同意票总数
  TextView textVetoCount;// 否决票总数

  public MeetOperationView(@NonNull Context context) {
    this(context, null);
  }

  public MeetOperationView(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public MeetOperationView(@NonNull Context context, @Nullable AttributeSet attrs,
      @AttrRes int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    inflate(context, R.layout.layout_meet_operation, this);
    init();
  }

  private void init() {
    llVoteContainer = (LinearLayout) findViewById(R.id.ll_vote_container);
    llContainer = (LinearLayout) findViewById(R.id.ll_container);

    btnSign = (Button) findViewById(R.id.btn_sign);
    textTitle = (TextView) findViewById(R.id.text_title);
    textContent = (TextView) findViewById(R.id.text_content);
    textAgreeCount = (TextView) findViewById(R.id.text_agree_count);
    textVetoCount = (TextView) findViewById(R.id.text_veto_count);
    btnSign.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        if (mOnClickListener != null) {
          mOnClickListener.onClick(v);
        }
      }
    });
    llContainer.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        if (mOnClickListener != null) {
          mOnClickListener.onClick(v);
        }
      }
    });
  }

  public void showSignBtn() {
    switchType(TYPE_SIGN);
  }

  public void showContentText(String count, int type) {
    switchType(TYPE_CONTENT);
    if (type == 0) { // 已签到%s人
      String text = getResources().getString(R.string.sign_count, count);
      textContent.setText(text);
    } else if (type == 1) { // 总票数: %s

    }
  }

  public void showNormalVote(String agreeCount, String vetoCount) {
    switchType(TYPE_NORMAL_VOTE);
    textAgreeCount.setText(agreeCount);
    textVetoCount.setText(vetoCount);
  }

  public void switchType(int type) {
    switch (type) {
      case TYPE_SIGN: { // 按钮状态
        btnSign.setVisibility(View.VISIBLE);
        llContainer.setVisibility(View.GONE);
      }
      break;
      case TYPE_CONTENT: {
        btnSign.setVisibility(View.GONE);
        llVoteContainer.setVisibility(View.GONE);
        llContainer.setVisibility(View.VISIBLE);
        textContent.setVisibility(View.VISIBLE);
      }
      break;
      case TYPE_NORMAL_VOTE: {
        btnSign.setVisibility(View.GONE);
        textContent.setVisibility(View.GONE);
        llContainer.setVisibility(View.VISIBLE);
        llVoteContainer.setVisibility(View.VISIBLE);
      }
      break;
    }
  }

  private MeetOperationViewOnClickListener mOnClickListener;

  public void setMeetOperationViewOnClickListener(
      MeetOperationViewOnClickListener onClickListener) {
    mOnClickListener = onClickListener;
  }

  public interface MeetOperationViewOnClickListener {
    void onClick(View view);
  }
}
