package youlin.xinhua.com.youlin.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.utils.LogUtils;
import youlin.xinhua.com.youlin.utils.ToastUtils;
import youlin.xinhua.com.youlin.widget.SwitchButton;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-07-13 14:37
 * version: 1.0
 * </pre>
 */

public class BtnActivity extends BaseActivity {

  public static void lunch(Context context) {
    Intent intent = new Intent(context, BtnActivity.class);
    context.startActivity(intent);
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_btn;
  }

  @BindView(R.id.yellow)        Button       yel;
  @BindView(R.id.blue)          Button       blue;
  @BindView(R.id.switch_button) SwitchButton mSwitchButton;
  @BindView(R.id.text)          TextView     textView;
  @BindView(R.id.btn_vote)      Button       btnVote;

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 51,
        getResources().getDisplayMetrics());
    int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 31,
        getResources().getDisplayMetrics());
    //mSwitchButton.setThumbSize(width, height);

  }

  @OnClick({ R.id.blue, R.id.yellow, R.id.btn_switch, R.id.text }) public void onClick(View view) {
    switch (view.getId()) {
      case R.id.blue:
        //countDownTimer();
        if (yel.isEnabled()) {
          yel.setEnabled(false);
        } else {
          yel.setEnabled(true);
        }
        break;
      case R.id.yellow:
        if (blue.isEnabled()) {
          blue.setEnabled(false);
        } else {
          blue.setEnabled(true);
        }
        break;
      case R.id.btn_switch:
        if (textView.isEnabled()) {
          textView.setEnabled(false);
          textView.setBackgroundResource(R.drawable.shape_gray_deep_fillet_15);
        } else {
          textView.setEnabled(true);
          textView.setBackgroundResource(R.drawable.shape_blue_fillet_15);
        }
        break;
      case R.id.text:
        ToastUtils.show("text click");
        break;
      default:
        break;
    }
  }

  void countDownTimer() {
    CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {
      @Override public void onTick(long millisUntilFinished) {
        LogUtils.i(millisUntilFinished);
      }

      @Override public void onFinish() {
        LogUtils.i("finish");
      }
    };
    countDownTimer.start();
  }
}
