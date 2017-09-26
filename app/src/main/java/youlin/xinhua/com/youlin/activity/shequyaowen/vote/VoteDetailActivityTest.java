package youlin.xinhua.com.youlin.activity.shequyaowen.vote;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import youlin.xinhua.com.youlin.BaseActivity;
import youlin.xinhua.com.youlin.R;
import youlin.xinhua.com.youlin.utils.LogUtils;
import youlin.xinhua.com.youlin.utils.MeasureUtils;
import youlin.xinhua.com.youlin.widget.dialog.TipDialog;

/**
 * <pre>
 * desc   : Todo
 * author : 罗顺翔
 * time   : 2017-09-04 15:38
 * version: 1.0
 * </pre>
 */

public class VoteDetailActivityTest extends BaseActivity {

  @BindView(R.id.seekBar)       SeekBar      mSeekBar;
  @BindView(R.id.ll_progress)   LinearLayout mLLProgress;
  @BindView(R.id.view_progress) View         mViewProgress;
  @BindView(R.id.text_number)   TextView     mTextNumber;
  @BindView(R.id.btn_animator)  Button       mButton;

  int measureWidth;
  int minWidth;

  ValueAnimator mValueAnimator;

  TipDialog mTipDialog;

  public static void launch(Context context) {
    Intent intent = new Intent(context, VoteDetailActivityTest.class);
    context.startActivity(intent);
  }

  @Override protected int attachLayoutRes() {
    return R.layout.activity_vote_detail_test;
  }

  @OnClick(R.id.btn_animator) public void click() {

    if (mTipDialog != null) {
      mTipDialog.cancel();
    }
    mTipDialog = new TipDialog(this, TipDialog.TIP_TYPE, true);
    mTipDialog.setTipText("你好").show();
    //mTipDialog.setTitleText("投票未开始")
    //    .setConfirmText("确定")
    //    .setConfirmClickListener(new TipDialog.OnSweetClickListener() {
    //      @Override public void onClick(TipDialog tipDialog) {
    //        //ToastUtils.show("aaa");
    //        tipDialog.changeAlertType(TipDialog.NORMAL_TYPE);
    //        tipDialog.setTitleText("你好")
    //            .setContentText("哈哈哈")
    //            .setCancelText("ixix1")
    //            .setConfirmClickListener(new TipDialog.OnSweetClickListener() {
    //              @Override public void onClick(TipDialog tipDialog) {
    //                tipDialog.changeAlertType(TipDialog.PROGRESS_TYPE);
    //              }
    //            });
    //      }
    //    })
    //    .show();

    //mTipDialog.changeAlertType(TipDialog.NORMAL_TYPE);
    //mTipDialog.setTitleText("投票确认")
    //    .setContentText("没人仅有一次投票机会,确认将无法修改")
    //    .setConfirmText("投票")
    //    .setCancelText("取消")
    //    .setConfirmClickListener(new TipDialog.OnSweetClickListener() {
    //      @Override public void onClick(TipDialog tipDialog) {
    //        tipDialog.changeAlertType(TipDialog.PROGRESS_TYPE);
    //        tipDialog.setContentText("提交中");
    //      }
    //    })
    //    .setCancelClickListener(new TipDialog.OnSweetClickListener() {
    //      @Override public void onClick(TipDialog tipDialog) {
    //        tipDialog.dismiss();
    //      }
    //    })
    //    .show();
  }

  private void showAnim() {
    mButton.setClickable(false);
    mValueAnimator = ValueAnimator.ofInt(0, 100);
    mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
      @Override public void onAnimationUpdate(ValueAnimator animation) {
        int value = (int) animation.getAnimatedValue();
        float p = value / 100f;
        int pWidth = (int) (p * measureWidth);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mViewProgress.getLayoutParams();
        lp.width = pWidth;
        mViewProgress.setLayoutParams(lp);
        mTextNumber.setText(value + "人");
      }
    });
    mValueAnimator.setDuration(500);
    mValueAnimator.addListener(new AnimatorListenerAdapter() {
      @Override public void onAnimationEnd(Animator animation) {
        super.onAnimationEnd(animation);
        mButton.setClickable(true);
      }
    });
    mValueAnimator.start();
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    minWidth = MeasureUtils.dp2px(15);
    mLLProgress.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
      @Override
      public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft,
          int oldTop, int oldRight, int oldBottom) {
        v.removeOnLayoutChangeListener(this);
        measureWidth = v.getMeasuredWidth();
        LogUtils.i("measureWidth : " + measureWidth);
      }
    });
    mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
      @Override public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        float p = progress / 100f;
        int pWidth = (int) (p * measureWidth);
        LogUtils.i(measureWidth + " , " + progress + " , " + pWidth);
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mViewProgress.getLayoutParams();
        lp.width = Math.max(pWidth, minWidth);
        mViewProgress.setLayoutParams(lp);
        mTextNumber.setText(progress + "人");
      }

      @Override public void onStartTrackingTouch(SeekBar seekBar) {

      }

      @Override public void onStopTrackingTouch(SeekBar seekBar) {

      }
    });
  }
}
