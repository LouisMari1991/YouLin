package youlin.xinhua.com.youlin.widget;

import android.content.Context;
import android.os.CountDownTimer;
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
import youlin.xinhua.com.youlin.utils.DateUtil;
import youlin.xinhua.com.youlin.utils.StringUtils;

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

  CountDownTimer mCountDownTimer;

  int curViewState = -1;

  /**
   * 下面为文字时 头部提示文字.
   *
   * 0: 签到状态 -> 已签到%s人
   *
   * 1: 投票结果公示: 总票数: %s票 (新成立业委会, 换届选举)
   *
   * 2: 投票中 -> 同意票%s, 否决票%s
   *
   * 3: 投票公示 -> 同意票%s, 否决票%s
   */
  private final String[] titleTextArray = { "签到状态", "投票结果公示", "投票中", "投票公示" };

  /**
   * 下面为倒计时的 头部提示文字 (新成立业委会, 换届选举).
   *
   * 0: 候选人自荐 -> 1天 12:12:25
   *
   * 1: 为候选人投票 -> 2天 08:12:25
   */
  private final String[] timerTextArray = { "候选人自荐", "为候选人投票" };

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

  /**
   * 设置显示content文字内容
   *
   * @param params 内容
   */
  public void setContentText(int type, String... params) {
    switch (type) {
      case 0: { // 签到状态 -> 已签到%s人
        switchType(TYPE_CONTENT);
        textTitle.setText(titleTextArray[type]);
        String text = getResources().getString(R.string.sign_count, params[0]);
        textContent.setText(StringUtils.getLightText(getContext(), text, params[0], R.color.white,
            R.color.color_yel_text));
      }
      break;
      case 1: { // 投票结果公示 -> 总票数: %s
        switchType(TYPE_CONTENT);
        textTitle.setText(titleTextArray[type]);
        String text = getResources().getString(R.string.tick_count, params[0]);
        textContent.setText(StringUtils.getLightText(getContext(), text, params[0], R.color.white,
            R.color.color_yel_text));
      }
      break;
      case 2: //投票中:  同意票 %s, 否决票 %s
      case 3: {
        switchType(TYPE_NORMAL_VOTE);
        textTitle.setText(titleTextArray[type]);
        textAgreeCount.setText(params[0]);
        textVetoCount.setText(params[1]);
      }
      break;
    }
  }

  /**
   * 开始倒计时
   *
   * @param arrayIndex 0: 候选人自荐, 1: 为候选人投票
   */
  public void startTimerCountDown(int arrayIndex, long time) {
    switchType(TYPE_CONTENT);
    textTitle.setText(timerTextArray[arrayIndex]);
    if (mCountDownTimer != null) {
      mCountDownTimer.cancel();
    }
    mCountDownTimer = new CountDownTimer(time, 1000) {
      @Override public void onTick(long millisUntilFinished) {
        textContent.setText(DateUtil.formatTimeDown(millisUntilFinished));
      }

      @Override public void onFinish() {
        // 倒计时结束
        if (mCountDownTimerListener != null) {
          mCountDownTimerListener.onFinish();
        }
      }
    }.start();
  }

  /**
   * 取消定时器
   */
  public void cancelTimer() {
    if (mCountDownTimer != null) {
      mCountDownTimer.cancel();
    }
  }

  public void switchType(int type) {
    if (curViewState == type) {
      return;
    }
    curViewState = type;
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
  private CountDownTimerListener           mCountDownTimerListener;

  public void setCountDownTimerListener(CountDownTimerListener countDownTimerListener) {
    mCountDownTimerListener = countDownTimerListener;
  }

  public void setMeetOperationViewOnClickListener(
      MeetOperationViewOnClickListener onClickListener) {
    mOnClickListener = onClickListener;
  }

  /**
   * 点击事件
   */
  public interface MeetOperationViewOnClickListener {
    void onClick(View view);
  }

  /**
   * 倒计时
   */
  public interface CountDownTimerListener {
    void onFinish();
  }
}
